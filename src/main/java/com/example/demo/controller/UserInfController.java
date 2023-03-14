package com.example.demo.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.example.demo.entity.RespBean;
import com.example.demo.entity.UserInf;
import com.example.demo.service.impl.UserInfServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;

/**
 * @description 用户信息
 * @author LB
 * @since 2023-02-25
 */

//@Log4j2
@Api(value = "用户", tags = {"用户信息的Api"})
@RestController
@RequestMapping("/user")
public class UserInfController {
    @Resource
    private UserInfServiceImpl userInfService;

    @SaCheckPermission(value = "user:view")
    @ApiOperation(value = "获取用户信息", notes = "获取用户信息", httpMethod = "GET")
    @GetMapping("/listUser")
    public RespBean getUserList() {
        return RespBean.ok("获取成功！", userInfService.list());
    }

    @SaCheckPermission(value = "user:add")
    @ApiOperation(value = "新增用户信息", notes = "新增用户信息", httpMethod = "POST")
    @PostMapping("/insertUser")
    public RespBean insertUser(@RequestBody(required = false) UserInf userInf) {
        if (userInf == null) {
            return RespBean.error("请求接口需要传递正确的JSON数据！");
        }
        return userInfService.insertUserInf(userInf);
    }

    @SaCheckPermission(value = "user:delete")
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息", httpMethod = "DELETE")
    @DeleteMapping("/deleteUser")
    public RespBean deleteUser(@RequestParam("loginName") String loginName) {
        return userInfService.deleteUserInf(loginName);
    }

    @SaCheckPermission(value = "user:update")
    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "POST")
    @PostMapping("/updateUser")
    public RespBean updateUser(@RequestBody(required = false) UserInf userInf) {
        if (userInf == null) {
            return RespBean.error("请求接口需要传递正确的JSON数据！");
        }
        return userInfService.updateUserInf(userInf);
    }

    @SaCheckPermission(value = "user:update")
    @ApiOperation(value = "更新用户密码", notes = "更新用户密码", httpMethod = "POST")
    @PostMapping("/updatePassword")
    public RespBean updatePassword(@RequestParam("loginName") String loginName,
                                   @RequestParam("oldPassword") String oldPassword,
                                   @RequestParam("newPassword") String newPassword) {
        return userInfService.updatePasswordByLoginName(loginName, oldPassword, newPassword);
    }
}