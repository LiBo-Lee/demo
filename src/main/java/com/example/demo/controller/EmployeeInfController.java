package com.example.demo.controller;


import com.example.demo.entity.EmployeeInf;
import com.example.demo.entity.RespBean;
import com.example.demo.service.impl.EmployeeInfServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Description 员工信息
 * @author LB
 * @since 2023-02-25
 */
@Log4j2
@Api(value = "员工", tags = {"员工信息的Api"})
@RestController
@RequestMapping("/employee")
public class EmployeeInfController {
    @Resource
    private EmployeeInfServiceImpl employeeInfService;

    @ApiOperation(value = "获取员工信息", notes = "获取员工信息", httpMethod = "GET")
    @GetMapping("/listEmployee")
    public RespBean getEmployeeList() {
        return employeeInfService.listEmployeeInfs();
    }

    @ApiOperation(value = "新增员工信息", notes = "新增员工信息", httpMethod = "POST")
    @PostMapping("/insertEmployee")
    public RespBean insertEmpoyee(@RequestBody(required = false) EmployeeInf employeeInf) {
        if (employeeInf == null) {
            return RespBean.error("请求接口需要传递正确的JSON数据！");
        }
        return employeeInfService.insertEmployeeInf(employeeInf);
    }

    @ApiOperation(value = "删除员工信息", notes = "删除员工信息", httpMethod = "DELETE")
    @DeleteMapping("/deleteEmployee")
    public RespBean deleteEmployee(Long id) {
        return employeeInfService.deleteEmployeeInf(id);
    }

    @ApiOperation(value = "更新员工信息", notes = "更新员工信息", httpMethod = "POST")
    @PostMapping("/updateEmployee")
    public RespBean updateEmployee(@RequestBody(required = false) EmployeeInf employeeInf) {
        if (employeeInf == null) {
            return RespBean.error("请求接口需要传递正确的JSON数据！");
        }
        return employeeInfService.updateEmployeeInf(employeeInf);
    }
}
