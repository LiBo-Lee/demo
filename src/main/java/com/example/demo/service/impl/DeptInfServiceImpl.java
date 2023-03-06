package com.example.demo.service.impl;

import com.example.demo.entity.DeptInf;
import com.example.demo.entity.RespBean;
import com.example.demo.mapper.DeptInfMapper;
import com.example.demo.service.IDeptInfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LB
 * @since 2023-02-25
 */
@Service
public class DeptInfServiceImpl extends ServiceImpl<DeptInfMapper, DeptInf> implements IDeptInfService {
    @Autowired
    private DeptInfMapper deptInfMapper;

    @Override
    public RespBean insertDept(DeptInf deptInf) {
        if (deptInf == null) {
            return RespBean.error("部门信息不能为空！");
        }
        String name = deptInf.getName();
        if (name == null || name.trim().equals("")) {
            return RespBean.error("部门名称不允许为空！");
        }
        if (deptInfMapper.countDeptByName(name) <= 0) {
            if (this.save(deptInf)) {
                return RespBean.ok("新增成功！");
            } else {
                return RespBean.error("新增失败！");
            }
        } else {
            return RespBean.error("新增失败！原因：部门名称" + name + "已存在，请更换！");
        }
    }

    @Override
    public RespBean deleteDeptById(Long id) {
        if (id == 0) {
            return RespBean.error("请提供部门id！");
        }
        if (deptInfMapper.countDeptById(id) <= 0 ) {
            return RespBean.error("该部门已不存在！");
        }
        if (this.removeById(id)) {
            return RespBean.ok("删除成功！");
        } else {
            return RespBean.error("删除失败！");
        }
    }

    @Override
    public RespBean deleteDeptByName(String name) {
        if (name == null || name.trim().equals("")) {
            return RespBean.error("请提供部门名称！");
        }
        if (deptInfMapper.countDeptByName(name) <= 0 ) {
            return RespBean.error("该部门已不存在！");
        }
        if (deptInfMapper.deleteDeptByName(name) <= 0) {
            return RespBean.error("删除失败！");
        }
        return RespBean.ok("删除成功！");
    }

    @Override
    public RespBean updateDept(DeptInf deptInf) {
        if (deptInf == null) {
            return RespBean.error("请求接口需要传递正确的JSON数据！");
        }
        if (deptInf.getName().trim().equals("")) {
            return RespBean.error("部门名称不允许为空！");
        }
        if (this.updateById(deptInf)) {
            return RespBean.ok("更新成功！");
        } else {
            return RespBean.error("更新失败！");
        }
    }
}
