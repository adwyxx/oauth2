package com.adwyxx.oauth.service.impl;

import com.adwyxx.oauth.model.AccessToken;
import com.adwyxx.oauth.model.AuthClientDetails;
import com.adwyxx.oauth.model.AuthUserDetails;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.*;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/7 14:40
 */
public class AccessTokenService implements AuthorizationServerTokenServices {
    private int refreshTokenValiditySeconds = 2592000;
    private int accessTokenValiditySeconds = 43200;
    private TokenStore tokenStore;
    private ClientDetailsService clientDetailsService;
//    private TokenEnhancer tokenEnhancer;
    private boolean supportRefreshToken=true;
    private boolean reuseRefreshToken=true;
    private AuthenticationManager authenticationManager;

    public TokenStore getTokenStore() {
        return tokenStore;
    }

    public void setTokenStore(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    public ClientDetailsService getClientDetailsService() {
        return clientDetailsService;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

//    public TokenEnhancer getTokenEnhancer() {
//        return tokenEnhancer;
//    }
//
//    public void setTokenEnhancer(TokenEnhancer tokenEnhancer) {
//        this.tokenEnhancer = tokenEnhancer;
//    }

    public boolean isSupportRefreshToken() {
        return supportRefreshToken;
    }

    public void setSupportRefreshToken(boolean supportRefreshToken) {
        this.supportRefreshToken = supportRefreshToken;
    }

    public boolean isReuseRefreshToken() {
        return reuseRefreshToken;
    }

    public void setReuseRefreshToken(boolean reuseRefreshToken) {
        this.reuseRefreshToken = reuseRefreshToken;
    }


    public AccessTokenService()
    {
        //this.tokenEnhancer=(TokenEnhancer)new DefaultAccessTokenConverter();
    }

    public AccessTokenService(TokenStore tokenStore, ClientDetailsService clientDetailsService)
    {
        this.tokenStore=tokenStore;
        this.supportRefreshToken=true;
        this.reuseRefreshToken=true;
        this.clientDetailsService=clientDetailsService;
        //this.tokenEnhancer = (TokenEnhancer)new DefaultAccessTokenConverter();
    }

    @Override
    public OAuth2AccessToken createAccessToken(OAuth2Authentication authentication) throws AuthenticationException {
        OAuth2AccessToken existingAccessToken = this.tokenStore.getAccessToken(authentication);
        OAuth2RefreshToken refreshToken = null;
        if (existingAccessToken != null) {
            if (!existingAccessToken.isExpired()) {
                this.tokenStore.storeAccessToken(existingAccessToken, authentication);
                return existingAccessToken;
            }

            if (existingAccessToken.getRefreshToken() != null) {
                refreshToken = existingAccessToken.getRefreshToken();
                this.tokenStore.removeRefreshToken(refreshToken);
            }

            this.tokenStore.removeAccessToken(existingAccessToken);
        }

        if (refreshToken == null) {
            refreshToken = this.createRefreshToken(authentication);
        } else if (refreshToken instanceof ExpiringOAuth2RefreshToken) {
            ExpiringOAuth2RefreshToken expiring = (ExpiringOAuth2RefreshToken)refreshToken;
            if (System.currentTimeMillis() > expiring.getExpiration().getTime()) {
                refreshToken = this.createRefreshToken(authentication);
            }
        }

        OAuth2AccessToken accessToken = this.createAccessToken(authentication, refreshToken);
        //保存token
        this.tokenStore.storeAccessToken(accessToken, authentication);
        refreshToken = accessToken.getRefreshToken();
        if (refreshToken != null) {
            //保存refreshToken
            this.tokenStore.storeRefreshToken(refreshToken, authentication);
        }
        return accessToken;
    }

    @Override
    public OAuth2AccessToken refreshAccessToken(String refreshTokenValue, TokenRequest tokenRequest) throws AuthenticationException {
        if (!this.supportRefreshToken) {
            throw new InvalidGrantException("Invalid refresh token: " + refreshTokenValue);
        } else {
            OAuth2RefreshToken refreshToken = this.tokenStore.readRefreshToken(refreshTokenValue);
            if (refreshToken == null) {
                throw new InvalidGrantException("Invalid refresh token: " + refreshTokenValue);
            } else {
                OAuth2Authentication authentication = this.tokenStore.readAuthenticationForRefreshToken(refreshToken);
                if (this.authenticationManager != null && !authentication.isClientOnly()) {
                    Authentication user = new PreAuthenticatedAuthenticationToken(authentication.getUserAuthentication(), "", authentication.getAuthorities());
                    user = this.authenticationManager.authenticate(user);
                    Object details = authentication.getDetails();
                    authentication = new OAuth2Authentication(authentication.getOAuth2Request(), user);
                    authentication.setDetails(details);
                }

                String clientId = authentication.getOAuth2Request().getClientId();
                if (clientId != null && clientId.equals(tokenRequest.getClientId())) {
                    this.tokenStore.removeAccessTokenUsingRefreshToken(refreshToken);
                    if (this.isExpired(refreshToken)) {
                        this.tokenStore.removeRefreshToken(refreshToken);
                        throw new InvalidTokenException("Invalid refresh token (expired): " + refreshToken);
                    } else {
                        authentication = this.createRefreshedAuthentication(authentication, tokenRequest);
                        if (!this.reuseRefreshToken) {
                            this.tokenStore.removeRefreshToken(refreshToken);
                            refreshToken = this.createRefreshToken(authentication);
                        }

                        OAuth2AccessToken accessToken = this.createAccessToken(authentication, refreshToken);
                        //存储Token
                        this.tokenStore.storeAccessToken(accessToken, authentication);
                        if (!this.reuseRefreshToken) {
                            //存储RefreshToken
                            this.tokenStore.storeRefreshToken(accessToken.getRefreshToken(), authentication);
                        }

                        return accessToken;
                    }
                } else {
                    throw new InvalidGrantException("Wrong client for this refresh token: " + refreshTokenValue);
                }
            }
        }
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken token = this.tokenStore.getAccessToken(authentication);
        if(token instanceof AccessToken)
        {
            return (AccessToken)token;
        }
        return token;
    }

    private OAuth2Authentication createRefreshedAuthentication(OAuth2Authentication authentication, TokenRequest request) {
        Set<String> scope = request.getScope();
        OAuth2Request clientAuth = authentication.getOAuth2Request().refresh(request);
        if (scope != null && !scope.isEmpty()) {
            Set<String> originalScope = clientAuth.getScope();
            if (originalScope == null || !originalScope.containsAll(scope)) {
                throw new InvalidScopeException("Unable to narrow the scope of the client authentication to " + scope + ".", originalScope);
            }

            clientAuth = clientAuth.narrowScope(scope);
        }

        OAuth2Authentication narrowed = new OAuth2Authentication(clientAuth, authentication.getUserAuthentication());
        return narrowed;
    }

    private OAuth2AccessToken createAccessToken(OAuth2Authentication authentication, OAuth2RefreshToken refreshToken) {
        AccessToken token = new AccessToken(UUID.randomUUID().toString());
        int validitySeconds = this.getAccessTokenValiditySeconds(authentication.getOAuth2Request());
        if (validitySeconds > 0) {
            token.setExpiration(new Date(System.currentTimeMillis() + (long)validitySeconds * 1000L));
        }

        Object obj1 = authentication.getPrincipal();
        if(obj1 instanceof AuthUserDetails)
        {
            AuthUserDetails user = (AuthUserDetails) obj1;
            token.setUserId(user.getId());
            token.setUsername(user.getUsername());
        }

        Object obj2= authentication.getDetails();
        if(obj2 instanceof AuthClientDetails)
        {
            AuthClientDetails details = (AuthClientDetails) obj2;
            token.setClientId(details.getClientId());
        }

        token.setRefreshToken(refreshToken);
        token.setScope(authentication.getOAuth2Request().getScope());
        return token;
        //return (OAuth2AccessToken)(this.tokenEnhancer != null ? this.tokenEnhancer.enhance(token, authentication) : token);
    }

    private OAuth2RefreshToken createRefreshToken(OAuth2Authentication authentication) {
        if (!this.isSupportRefreshToken(authentication.getOAuth2Request())) {
            return null;
        } else {
            int validitySeconds = this.getRefreshTokenValiditySeconds(authentication.getOAuth2Request());
            String value = UUID.randomUUID().toString();
            return (OAuth2RefreshToken)(validitySeconds > 0 ? new DefaultExpiringOAuth2RefreshToken(value, new Date(System.currentTimeMillis() + (long)validitySeconds * 1000L)) : new DefaultOAuth2RefreshToken(value));
        }
    }

    protected int getRefreshTokenValiditySeconds(OAuth2Request clientAuth) {
        if (this.clientDetailsService != null) {
            ClientDetails client = this.clientDetailsService.loadClientByClientId(clientAuth.getClientId());
            Integer validity = client.getRefreshTokenValiditySeconds();
            if (validity != null) {
                return validity;
            }
        }
        return this.refreshTokenValiditySeconds;
    }

    protected int getAccessTokenValiditySeconds(OAuth2Request clientAuth) {
        if (this.clientDetailsService != null) {
            ClientDetails client = this.clientDetailsService.loadClientByClientId(clientAuth.getClientId());
            Integer validity = client.getAccessTokenValiditySeconds();
            if (validity != null) {
                return validity;
            }
        }

        return this.accessTokenValiditySeconds;
    }

    protected boolean isSupportRefreshToken(OAuth2Request clientAuth) {
        if (this.clientDetailsService != null) {
            ClientDetails client = this.clientDetailsService.loadClientByClientId(clientAuth.getClientId());
            return client.getAuthorizedGrantTypes().contains("refresh_token");
        } else {
            return this.supportRefreshToken;
        }
    }

    protected boolean isExpired(OAuth2RefreshToken refreshToken) {
        if (!(refreshToken instanceof ExpiringOAuth2RefreshToken)) {
            return false;
        } else {
            ExpiringOAuth2RefreshToken expiringToken = (ExpiringOAuth2RefreshToken)refreshToken;
            return expiringToken.getExpiration() == null || System.currentTimeMillis() > expiringToken.getExpiration().getTime();
        }
    }
}
