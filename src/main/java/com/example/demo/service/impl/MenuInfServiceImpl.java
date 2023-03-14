package com.example.demo.service.impl;

import com.example.demo.entity.MenuInf;
import com.example.demo.mapper.MenuInfMapper;
import com.example.demo.service.IMenuInfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author LB
 * @since 2023-03-10
 */
@Service
public class MenuInfServiceImpl extends ServiceImpl<MenuInfMapper, MenuInf> implements IMenuInfService {
    @Resource
    private MenuInfMapper menuInfMapper;

    /**
     * 根据角色id返回权限列表
     *
     * @param roleId 角色id
     * @return java.util.List<java.lang.String> 权限列表
     * @author LiBo
     * @date 2023-03-10 17:57
     * */
    @Override
    public List<String> getPermsByRoleId(Integer roleId) {
        List<MenuInf> listMenuInf = menuInfMapper.getMenuInfoListsByRoleId(roleId);
        return listMenuInf.stream().map(MenuInf::getPerms).collect(Collectors.toList());
    }
}
