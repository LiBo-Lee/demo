package com.example.demo.controller;

import com.example.demo.entity.RespBean;
import com.example.demo.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeContoller {
    //引入EmployeeMapper这个接口
    @Autowired
    EmployeeMapper employeeMapper;
    //调用EmployeeMapper内的方法把employeeMapper.findAll()返回给List<Employee>
    @GetMapping("/list")
    public RespBean getEmployee() {
        return RespBean.ok("获取成功！", employeeMapper.findAll());
    }

    @GetMapping("/{id}")
    public RespBean getEmployeeById(@PathVariable("id") long id) {
        return RespBean.ok("获取成功！", employeeMapper.findEmployeeById(id));
    }

    @GetMapping("/check/{id}")
    public RespBean checkEmployeeById(@PathVariable("id") long id){
        if (employeeMapper.checkEmployeeById(id) == 1){
            return RespBean.ok("员工序号" + id + "已找到！");
        }
        return RespBean.error("员工序号" + id + "未找到！");
    }

    @DeleteMapping("/del/{id}")
    public RespBean deleteEmployeeById(@PathVariable("id") long id){
        if (employeeMapper.checkEmployeeById(id) > 0){
            if (employeeMapper.delEmployeeById(id) > 0){
                return RespBean.ok("删除成功！");
            }
            return RespBean.error("删除失败！");
        }
        return RespBean.error("员工序号" + id + "未找到！");
    }
}
