package com.example.demo.mapper;

import com.example.demo.entity.UserInf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LB
 * @since 2023-02-25
 */
@Mapper
@Repository
public interface UserInfMapper extends BaseMapper<UserInf> {
    /**
     * 新增用户信息
     *
     * @param userInf 用户信息
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-01 14:24
     * */
    Integer insertUserInf(@Param("userInf") UserInf userInf);

    /**
     * 删除用户信息
     *
     * @param loginname 登录用户名
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-01 16:27
     * */
    Integer deleteUserInf(@Param("loginName") String loginName);

    /**
     * 更新用户信息
     *
     * @param userInf 用户信息
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-01 16:27
     * */
    Integer updateUserInf(@Param("userInf") UserInf userInf);

    /**
     * 更新用户密码（每次更新会自动更新盐值）
     *
     * @param loginName 登录名
     * @param password 登录密码（密文）
     * @param salt 盐
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-11 14:44
     * */
    Integer updatePasswordByLoginName(@Param("loginName") String loginName, @Param("password") String password, @Param("salt") String salt);
}
