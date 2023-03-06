package com.example.demo.service;

import com.example.demo.entity.DeptInf;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LB
 * @since 2023-02-25
 */
public interface IDeptInfService extends IService<DeptInf> {
    RespBean insertDept(DeptInf deptInf);

    RespBean deleteDeptById(Long id);

    RespBean deleteDeptByName(String name);

    RespBean updateDept(DeptInf deptInf);
}
