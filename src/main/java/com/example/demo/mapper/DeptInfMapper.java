package com.example.demo.mapper;

import com.example.demo.entity.DeptInf;
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
public interface DeptInfMapper extends BaseMapper<DeptInf> {
    /**
     * 检测部门ID是否存在
     *
     * @param id 部门ID
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-03 11:41
     * */
    Integer countDeptById(@Param("id") Long id);

    /**
     * 检测部门名称是否存在
     *
     * @param name 部门名称
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-03 11:42
     * */
    Integer countDeptByName(@Param("name") String name);

    /** 
     * 根据部门名称来删除部门信息
     * 
     * @param name 部门名称
     * @return java.lang.Integer 
     * @author LiBo
     * @date 2023-03-03 15:50
     * */ 
    Integer deleteDeptByName(@Param("name") String name);
}
