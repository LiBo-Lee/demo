package com.example.demo.controller;

import com.example.demo.entity.RespBean;
import com.example.demo.service.impl.AuthServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author LiBo
 * @date 2023-03-03 16:45
 **/

@Api(value = "用户登录", tags = "用户登录Api")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private AuthServiceImpl authService;

    @ApiOperation(value = "用户登录并授权", notes = "用户登录并授权", httpMethod = "POST")
    @PostMapping("/login")
    public RespBean login(@RequestParam("loginname") String loginname, @RequestParam("password") String password) {
        return authService.login(loginname, password);
    }
}
