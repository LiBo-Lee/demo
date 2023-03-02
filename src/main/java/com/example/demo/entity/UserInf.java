package com.example.demo.entity;

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
 * <p>
 * 
 * </p>
 *
 * @author LB
 * @since 2023-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_inf")
@ApiModel(value="UserInf对象", description="系统用户")
public class UserInf extends Model<UserInf> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("登录名")
    private String loginname;

    @ApiModelProperty("登录密码")
    private String password;

    @ApiModelProperty("状态")
    private Integer status;

    @ApiModelProperty("创建日期")
    private String createdate;

    @ApiModelProperty("登录名称")
    private String username;


}
