package com.example.demo.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.RespBean;
import com.example.demo.entity.UserInf;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LB
 * @since 2023-02-25
 */
public interface IUserInfService extends IService<UserInf> {
    public RespBean getUserList();

    public RespBean insertUserInf(UserInf userInf);

    public RespBean deleteUserInf(String loginname);

    public RespBean updateUserInf(UserInf userInf);
}
