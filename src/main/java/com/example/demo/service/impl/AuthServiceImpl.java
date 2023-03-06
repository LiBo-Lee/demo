package com.example.demo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.entity.RespBean;
import com.example.demo.entity.datas.AuthMode;
import com.example.demo.mapper.UserInfMapper;
import com.example.demo.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LiBo
 * @date 2023-03-03 16:52
 **/

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserInfMapper userInfMapper;

    @Override
    public RespBean login(String loginname, String password) {
        if (loginname == null || loginname.trim().equals("")) {
            return RespBean.error("请提供用户登录名！");
        }
        if (userInfMapper.countUserByLoginName(loginname) <= 0) {
            return RespBean.error("该用户登录名（" + loginname + "）不存在！");
        }
        if (userInfMapper.countUserByNP(loginname, password) <= 0) {
            return RespBean.error("用户登录失败！");
        }
        StpUtil.login(loginname);
        return RespBean.ok("登录成功！", new AuthMode(loginname, StpUtil.getTokenValue()));
    }
}
