package com.example.demo.mapper;

import com.example.demo.entity.MenuInf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜单权限表 Mapper 接口
 *
 * @author LB
 * @since 2023-03-10
 */

@Mapper
@Repository
public interface MenuInfMapper extends BaseMapper<MenuInf> {
    /**
     * 根据角色id获取对应菜单信息
     *
     * @param roleId 角色id
     * @return java.util.List<com.example.demo.entity.MenuInf>
     * @author LiBo
     * @date 2023-03-10 17:49
     * */
    List<MenuInf> getMenuInfoListsByRoleId(@Param("roleId") Integer roleId);
}
