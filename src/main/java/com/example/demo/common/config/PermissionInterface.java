package com.example.demo.common.config;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.example.demo.entity.UserInf;
import com.example.demo.service.impl.MenuInfServiceImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @author LiBo
 * @date 2023-03-10 17:58
 **/

@Component
public class PermissionInterface implements StpInterface {
    @Resource
    private MenuInfServiceImpl menuInfService;

    @Override
    public List<String> getPermissionList(Object o, String s) {
        List<String> roleIdList = getRoleList(o, s);
        Integer roleId = Integer.parseInt(roleIdList.get(0));
        return menuInfService.getPermsByRoleId(roleId);
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        UserInf userInf = (UserInf) StpUtil.getSession().get("userInf");
        return Arrays.asList(userInf.getRoleIds());
    }
}
