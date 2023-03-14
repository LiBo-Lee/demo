package com.example.demo.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.example.demo.entity.DeptInf;
import com.example.demo.entity.RespBean;
import com.example.demo.service.impl.DeptInfServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
//import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @description 部门
 * @author LB
 * @since 2023-02-25
 */

//@Log4j2
@Api(value = "部门", tags = {"部门信息的Api"})
@RestController
@RequestMapping("/dept")
public class DeptInfController {
    @Resource
    public DeptInfServiceImpl deptInfService;

    @SaCheckPermission(value = "dept:view")
    @ApiOperation(value = "获取部门信息", notes = "获取部门信息", httpMethod = "GET")
    @GetMapping("/listDept")
    public RespBean getUserList() {
        return RespBean.ok("获取成功！", deptInfService.list());
    }

    @SaCheckPermission(value = "dept:add")
    @ApiOperation(value = "新增部门信息", notes = "新增部门信息", httpMethod = "POST")
    @PostMapping("/insertDept")
    public RespBean insertDept(@RequestBody(required = false) DeptInf deptInf) {
        if (deptInf == null) {
            return RespBean.error("请求接口需要传递正确的JSON数据！");
        }
        return deptInfService.insertDept(deptInf);
    }

    @SaCheckPermission(value = "dept:delete")
    @ApiOperation(value = "删除部门信息（部门ID）", notes = "删除部门信息（部门ID）", httpMethod = "DELETE")
    @DeleteMapping("/deleteDept/{id}")
    public RespBean deleteDept(@PathVariable("id") Long id) {
        return deptInfService.deleteDeptById(id);
    }

    @SaCheckPermission(value = "dept:delete")
    @ApiOperation(value = "删除部门信息（部门名称）", notes = "删除部门信息（部门名称）", httpMethod = "DELETE")
    @DeleteMapping("/deleteDeptByName")
    public RespBean deleteDeptByName(@RequestParam("name") String name) {
        return deptInfService.deleteDeptByName(name);
    }

    @SaCheckPermission(value = "dept:update")
    @ApiOperation(value = "更新部门信息", notes = "更新部门信息", httpMethod = "POST")
    @PostMapping("/updateDept")
    public RespBean updateDept(@RequestBody DeptInf deptInf) {
        return deptInfService.updateDept(deptInf);
    }
}
