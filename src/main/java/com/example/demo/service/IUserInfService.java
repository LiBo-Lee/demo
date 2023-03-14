package com.example.demo.service;

import com.example.demo.entity.RespBean;
import com.example.demo.entity.UserInf;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LB
 * @since 2023-02-25
 */
public interface IUserInfService extends IService<UserInf> {
    RespBean insertUserInf(UserInf userInf);

    RespBean deleteUserInf(String loginName);

    RespBean updateUserInf(UserInf userInf);

    RespBean updatePasswordByLoginName(String loginName, String oldPassword, String newPassword);
}
