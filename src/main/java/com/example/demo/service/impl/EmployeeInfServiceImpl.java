package com.example.demo.service.impl;

import com.example.demo.entity.EmployeeInf;
import com.example.demo.entity.RespBean;
import com.example.demo.mapper.EmployeeInfMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.service.IEmployeeInfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LB
 * @since 2023-02-25
 */
@Service
public class EmployeeInfServiceImpl extends ServiceImpl<EmployeeInfMapper, EmployeeInf> implements IEmployeeInfService {
    @Resource
    private EmployeeInfMapper employeeInfMapper;

    public RespBean listEmployeeInfs() {
        return RespBean.ok("获取成功！", employeeInfMapper.listEmployeeInfs());
    }

    public RespBean insertEmployeeInf(EmployeeInf employeeInf) {
        if (employeeInf == null) {
            return RespBean.error("请求接口需要传递正确的JSON数据！");
        }
        if (employeeInf.getName().trim().equals("")) {
            return RespBean.error("员工姓名不允许为空！");
        }
        if (employeeInfMapper.countEmployeeByNameCardId(employeeInf.getName(), employeeInf.getCardId()) > 0) {
            return RespBean.error("该员工信息已存在，请确认信息！");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = new Date(System.currentTimeMillis());
        employeeInf.setCreateDate(simpleDateFormat.format(newDate));
        employeeInf.setUpdateDate(simpleDateFormat.format(newDate));
        if (employeeInfMapper.insertEmployeeInf(employeeInf) <= 0) {
            return RespBean.error("新增失败！");
        }
        return RespBean.ok("新增成功！");
    }

    public RespBean deleteEmployeeInf(Long id) {
        if (id == 0) {
            return RespBean.error("请提供正确的员工id！");
        }
        if (employeeInfMapper.countEmployeeById(id) <= 0) {
            return RespBean.error("该员工id已不存在！");
        }
        if (employeeInfMapper.deleteEmployeeInf(id) <= 0) {
            return RespBean.error("删除失败！");
        }
        return RespBean.ok("删除成功！");
    }

    public RespBean updateEmployeeInf(EmployeeInf employeeInf) {
        if (employeeInf == null) {
            return RespBean.error("请求接口需要传递正确的JSON数据！");
        }
        if (employeeInf.getName().trim().equals("")) {
            return RespBean.error("员工姓名不允许为空！");
        }
        EmployeeInf employeeInf1 = employeeInfMapper.getEmployeeInfByNameCardId(employeeInf.getName(), employeeInf.getCardId());
        if (employeeInf1 != null && !employeeInf1.getId().equals(employeeInf.getId())) {
            return RespBean.error("该员工信息已存在，请确认信息！");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = new Date(System.currentTimeMillis());
        employeeInf.setUpdateDate(simpleDateFormat.format(newDate));
        if (employeeInfMapper.updateEmployeeInf(employeeInf) <= 0) {
            return RespBean.error("更新失败！");
        }
        return RespBean.ok("更新成功！");
    }
}
