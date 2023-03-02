package com.example.demo.service;

import com.example.demo.entity.EmployeeInf;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.RespBean;
import com.example.demo.mapper.EmployeeInfMapper;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LB
 * @since 2023-02-25
 */
public interface IEmployeeInfService extends IService<EmployeeInf> {
    public RespBean listEmployeeInfs();

    public RespBean insertEmployeeInf(EmployeeInf employeeInf);

    public RespBean deleteEmployeeInf(Long id);

    public RespBean updateEmployeeInf(EmployeeInf employeeInf);
}
