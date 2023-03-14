package com.example.demo.controller;

import com.example.demo.entity.RespBean;
import com.example.demo.service.impl.AuthServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author LiBo
 * @date 2023-03-03 16:45
 **/

//@Log4j2
@Api(value = "用户登录", tags = "用户登录Api")
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Resource
    private AuthServiceImpl authService;

    @ApiOperation(value = "用户登录并授权", notes = "用户登录并授权", httpMethod = "POST")
    @PostMapping("/login")
    public RespBean login(HttpServletRequest request) {
        String loginname = request.getHeader("loginname");
        String password = request.getHeader("password");
        return authService.login(loginname, password);
    }

    @ApiOperation(value = "用户登出", notes = "用户登出", httpMethod = "DELETE")
    @DeleteMapping("/logout")
    public RespBean logout(@RequestParam("loginname") String loginname, @RequestParam("token") String token) {
        return authService.logout(loginname, token);
    }
}
