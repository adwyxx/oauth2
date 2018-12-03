package com.adwyxx.oauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Auther: Leo.W
 * @Date: 2018/12/3 17:34
 */
@RestController
@RequestMapping("/encoder")
public class EncoderController {

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;

    @GetMapping("/encode/{value}")
    public String encode(@PathVariable("value") String value)
    {
        return cryptPasswordEncoder.encode(value);
    }
}
