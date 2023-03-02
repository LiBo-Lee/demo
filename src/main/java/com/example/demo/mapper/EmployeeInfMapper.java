package com.example.demo.mapper;

import com.example.demo.entity.EmployeeInf;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface EmployeeInfMapper extends BaseMapper<EmployeeInf> {
    /**
     * 获取所有员工信息
     *
     * @return java.util.List<com.example.demo.entity.EmployeeInf>
     * @author LiBo
     * @date 2023-03-01 11:49
     * */
    List<EmployeeInf> listEmployeeInfs();

    /**
     * 根据员工姓名和员工证件号返回员工信息
     *
     * @param name: 员工姓名
     * @param cardId: 员工证件号
     * @return com.example.demo.entity.EmployeeInf
     * @author LiBo
     * @date 2023-03-01 14:59
     * */
    EmployeeInf getEmployeeInfByNameCardId(@Param("name") String name, @Param("cardId") String cardId);

    /**
     * 根据员工id检测员工是否存在
     *
     * @param id : 员工编号
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-01 11:49
     * */
    Integer countEmployeeById(@Param("id") Long id);

    /**
     * 根据员工姓名和证件号检测员工信息是否存在
     *
     * @param name: 员工姓名
     * @param cardId：员工证件号
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-01 14:19
     * */
    Integer countEmployeeByNameCardId(@Param("name") String name, @Param("cardId") String cardId);

    /**
     * 新增员工信息
     *
     * @param employeeInf: 员工信息
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-01 14:20
     * */
    Integer insertEmployeeInf(@Param("emp") EmployeeInf employeeInf);

    /**
     * 根据员工id删除员工信息
     *
     * @param id: 员工id
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-01 15:03
     * */
    Integer deleteEmployeeInf(@Param("id") Long id);

    /**
     * 更新员工信息
     *
     * @param employeeInf: 员工信息
     * @return java.lang.Integer
     * @author LiBo
     * @date 2023-03-01 15:04
     * */
    Integer updateEmployeeInf(@Param("emp") EmployeeInf employeeInf);
}
