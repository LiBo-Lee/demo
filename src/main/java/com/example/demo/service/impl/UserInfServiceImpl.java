package com.example.demo.service.impl;

import com.example.demo.entity.EmployeeInf;
import com.example.demo.entity.RespBean;
import com.example.demo.entity.UserInf;
import com.example.demo.mapper.UserInfMapper;
import com.example.demo.service.IUserInfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class UserInfServiceImpl extends ServiceImpl<UserInfMapper, UserInf> implements IUserInfService {
    @Autowired
    private UserInfMapper userInfMapper;

    @Override
    public RespBean getUserList() {
        return RespBean.ok("获取成功！", userInfMapper.getUserList());
    }

    @Override
    public RespBean insertUserInf(UserInf userInf) {
        if (userInf == null) {
            return RespBean.error("用户信息不能为空！");
        }
        String loginname = userInf.getLoginname();
        String password = userInf.getPassword();
        String username = userInf.getUsername();
        if (loginname == null || loginname.trim().equals("")) {
            return RespBean.error("登录名不允许为空！");
        }
        if (password == null || password.trim().equals("")) {
            userInf.setPassword("123456");
        }
        if (username == null || username.trim().equals("")) {
            userInf.setUsername("default-test");
        }
        if (userInfMapper.countUserByLoginName(loginname) <= 0) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date newDate = new Date(System.currentTimeMillis());
            userInf.setCreatedate(simpleDateFormat.format(newDate));
            if (userInfMapper.insertUserInf(userInf) > 0) {
                return RespBean.ok("新增成功！");
            } else {
                return RespBean.error("新增失败！");
            }
        } else {
            return RespBean.error("新增失败！原因：用户名" + loginname + "已存在，请更换！");
        }
    }

    @Override
    public RespBean deleteUserInf(String loginname) {
        if (loginname == null || loginname == "") {
            return RespBean.error("请提供登录名！");
        }
        if (userInfMapper.countUserByLoginName(loginname) <= 0 ) {
            return RespBean.error("该用户已不存在！");
        }
        if (userInfMapper.deleteUserInf(loginname) <= 0) {
            return RespBean.error("删除失败！");
        }
        return RespBean.ok("删除成功！");
    }

    @Override
    public RespBean updateUserInf(UserInf userInf) {
        if (userInf == null) {
            return RespBean.error("请求接口需要传递正确的JSON数据！");
        }
        if (userInf.getUsername().trim().equals("")) {
            return RespBean.error("用户姓名不允许为空！");
        }
        if (userInfMapper.countUserByLoginName(userInf.getLoginname()) <= 0) {
            return RespBean.error("用户不存在，无法更新！");
        }
        if (userInfMapper.updateUserInf(userInf) <= 0) {
            return RespBean.error("更新失败！");
        }
        return RespBean.ok("更新成功！");
    }
}
