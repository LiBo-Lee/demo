package com.example.demo.service;

import com.example.demo.entity.MenuInf;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author LB
 * @since 2023-03-10
 */
public interface IMenuInfService extends IService<MenuInf> {
    List<String> getPermsByRoleId(Integer roleId);
}
