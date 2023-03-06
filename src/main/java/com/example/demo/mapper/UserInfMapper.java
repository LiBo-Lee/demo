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
     * 检测用户登录名是否重复
     *
     * @param loginname 用户登录名
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-02-28 14:20
     * */
    Integer countUserByLoginName(@Param("loginname") String loginname);

    /**
     * 检测用户登录名和密码
     *
     * @param loginname 用户登录名
     * @param password 用户密码
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-03 16:50
     * */
    Integer countUserByNP(@Param("loginname") String loginname, @Param("password") String password);

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
    Integer deleteUserInf(@Param("loginname") String loginname);

    /**
     * 更新用户信息
     *
     * @param userInf 用户信息
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-01 16:27
     * */
    Integer updateUserInf(@Param("userInf") UserInf userInf);
}
