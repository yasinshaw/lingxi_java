package com.lingxi.lingxi_java.common.controller;

import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonController {
    //设置好自己的七牛云存储的key和bucket
    @Value("${qiniu.ak}")
    private String ACCESS_KEY;
    @Value("${qiniu.sk}")
    private String SECRETE_KEY;
    @Value("${qiniu.bucket}")
    private String BUCKET;

    @GetMapping("/qiniu/token")
    public String getToken() {
        Auth AUTH = Auth.create(ACCESS_KEY, SECRETE_KEY);
        return AUTH.uploadToken(BUCKET);
    }
}
