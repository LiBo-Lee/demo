package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 员工
 *
 * @author LiBo
 * @since 2023-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("employee_inf")
@ApiModel(value="EmployeeInf对象", description="员工信息")
public class EmployeeInf extends Model<EmployeeInf> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("部门ID")
    @TableField("dept_id")
    private Integer deptId;

    @ApiModelProperty("工作ID")
    @TableField("job_id")
    private Integer jobId;

    @ApiModelProperty("员工姓名")
    private String name;

    @ApiModelProperty("证件号")
    @TableField("card_id")
    private String cardId;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("邮政编码")
    @TableField("post_code")
    private String postCode;

    @ApiModelProperty("电话")
    private String tel;

    @ApiModelProperty("手机")
    private String phone;

    @ApiModelProperty("QQ号")
    @TableField("qq_num")
    private String qqNum;

    @ApiModelProperty("电子邮箱")
    private String email;

    @ApiModelProperty(value = "性别", notes = "1：男，2：女")
    private Integer sex;

    @ApiModelProperty("成分")
    private String party;

    @ApiModelProperty("生日")
    private String birthday;

    @ApiModelProperty("民族")
    private String race;

    @ApiModelProperty("学历")
    private String education;

    @ApiModelProperty("特长")
    private String speciality;

    @ApiModelProperty("爱好")
    private String hobby;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("创建时间")
    @TableField("create_date")
    private String createDate;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("更新时间")
    @TableField("update_date")
    private String updateDate;
}
