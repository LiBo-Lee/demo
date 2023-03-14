package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.util.EncryptionUtil;
import com.example.demo.common.vo.ExceptionVo;
import com.example.demo.entity.RespBean;
import com.example.demo.entity.UserInf;
import com.example.demo.mapper.UserInfMapper;
import com.example.demo.service.IUserInfService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LB
 * @since 2023-02-25
 */
@Service
public class UserInfServiceImpl extends ServiceImpl<UserInfMapper, UserInf> implements IUserInfService {
    @Resource
    private UserInfMapper userInfMapper;

    @Override
    public RespBean insertUserInf(UserInf userInf) {
        try {
            UserInf userInfNew = userInfCheckAndRebuild("add", userInf, "", "", "");
            if (userInfMapper.insertUserInf(userInfNew) > 0) {
                return RespBean.ok("新增成功！");
            } else {
                return RespBean.error("新增失败！");
            }
        } catch (ExceptionVo e) {
            return RespBean.error(e.getMsg());
        } catch (Exception e) {
            return RespBean.error(e.getMessage());
        }
    }

    @Override
    public RespBean deleteUserInf(String loginName) {
        try {
            UserInf userInfNew = userInfCheckAndRebuild("delete", null, loginName, "", "");
            if (userInfMapper.deleteUserInf(loginName) <= 0) {
                return RespBean.error("删除失败！");
            }
            return RespBean.ok("删除成功！");
        } catch (ExceptionVo e) {
            return RespBean.error(e.getMsg());
        } catch (Exception e) {
            return RespBean.error(e.getMessage());
        }
    }

    @Override
    public RespBean updateUserInf(UserInf userInf) {
        try {
            UserInf userInfNew = userInfCheckAndRebuild("update", userInf, "", "", "");
            if (userInfMapper.updateUserInf(userInfNew) <= 0) {
                return RespBean.error("更新失败！");
            }
            return RespBean.ok("更新成功！");
        } catch (ExceptionVo e) {
            return RespBean.error(e.getMsg());
        } catch (Exception e) {
            return RespBean.error(e.getMessage());
        }
    }

    @Override
    public RespBean updatePasswordByLoginName(String loginName, String oldPassword, String newPassword) {
        try {
            UserInf userInfNew = userInfCheckAndRebuild("updatePassword", null, loginName, oldPassword, newPassword);
            if (userInfMapper.updatePasswordByLoginName(loginName, userInfNew.getPassword(), userInfNew.getSalt()) <= 0) {
                return RespBean.error("删除失败！");
            }
            return RespBean.ok("删除成功！");
        } catch (ExceptionVo e) {
            return RespBean.error(e.getMsg());
        } catch (Exception e) {
            return RespBean.error(e.getMessage());
        }
    }

    /**
     * 校验函数。将所有操作的校验都使用同一方法。校验成功时返回重构后的用户信息对象
     *
     * @param acFlag 标志：add：新增；update：更新；delete：删除；updatePassword：更新密码
     * @param userInf 用户对象。当acFlag={“delete”，“updatePassword”}时可设置为null
     * @param loginName 登录名。当acFlag={“add”，“update”}时可设置为空
     * @param oldPassword 登录名。当acFlag={“add”，“update”，“delete“}时可设置为空
     * @param newPassword 登录名。当acFlag={“add”，“update”，“delete”}时可设置为空
     * @return com.example.demo.entity.UserInf 重构后的用户对象
     * @author LiBo
     * @date 2023-03-11 17:57
     * */
    private UserInf userInfCheckAndRebuild(String acFlag, UserInf userInf,  String loginName, String oldPassword, String newPassword) {
        // 当新增或更新时检测此项，若为删除或更新密码则新建一个（更新密码的时候也会把加密后的密码和新的盐值放入）
        if (acFlag.equals("add") || acFlag.equals("update")) {
            if (userInf == null) {
                throw new ExceptionVo(false, 500, "用户信息不能为空！");
            }
        } else {
            if (userInf == null) {
                userInf = new UserInf();
            }
        }
        // 检测登录名是否为空
        String loginNameCheck = loginName;
        if (acFlag.equals("add") || acFlag.equals("update")) {
            loginNameCheck = userInf.getLoginname();
        }
        if (loginNameCheck == null || loginNameCheck.trim().equals("")) {
            throw new ExceptionVo(false ,500, "登录名不允许为空！");
        }
        // 更新密码时检测新、旧密码是否为空
        if (acFlag.equals("updatePassword")) {
            if (oldPassword == null || oldPassword.trim().equals("")) {
                throw new ExceptionVo(false, 500, "旧密码不能为空！");
            }
            if (newPassword == null || newPassword.trim().equals("")) {
                throw new ExceptionVo(false, 500, "新密码不能为空！");
            }
        }
        // 根据登录名查询出整个信息，根据操作不同进行判断并抛出对应异常信息
        QueryWrapper<UserInf> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserInf::getLoginname, loginNameCheck);
        UserInf userInfOld = userInfMapper.selectOne(queryWrapper);
        if (acFlag.equals("add")) {
            if (userInfOld != null) {
                throw new ExceptionVo(false ,500, "登录名（" + loginNameCheck + "）已存在，无法新增！");
            }
        }
        if (acFlag.equals("update")) {
            if (userInfOld == null) {
                throw new ExceptionVo(false ,500, "登录名（" + loginNameCheck + "）不存在，无法更新！");
            }
        }
        if (acFlag.equals("delete")) {
            if (userInfOld == null) {
                throw new ExceptionVo(false ,500, "登录名（" + loginNameCheck + "）不存在，无法删除！");
            }
        }
        // 若为更新密码时则需要判断旧密码是否正确（相当于再次验证）和新旧密码是否相同
        if (acFlag.equals("updatePassword")) {
            if (userInfOld == null) {
                throw new ExceptionVo(false ,500, "登录名（" + loginNameCheck + "）不存在，无法更新密码！");
            }
            String oldEncodePassword = EncryptionUtil.getEncryptedPassword(oldPassword, userInfOld.getSalt());
            if (!oldEncodePassword.equals(userInfOld.getPassword())) {
                throw new ExceptionVo(false, 500, "旧密码错误，请输入正确密码！");
            }
            String newEncodePassword = EncryptionUtil.getEncryptedPassword(newPassword, userInfOld.getSalt());
            if (newEncodePassword.equals(userInfOld.getPassword())) {
                throw new ExceptionVo(false, 500, "新密码与旧密码相同，无需修改！");
            }
        }
        // 新增、更新时若密码为空，默认为：123456；更新密码时使用传入的新密码：newPassword
        String password = "";
        if (acFlag.equals("add") || acFlag.equals("update")) {
            password = userInf.getPassword();
            if (password == null || password.trim().equals("")) {
                password = "123456";
            }
        } else if (acFlag.equals("updatePassword")) {
            password = newPassword;
        }
        // 新增、更新密码时使用新的盐值；更新时若没有传入原盐值则生成新的盐值
        String salt = "";
        if (acFlag.equals("add") || acFlag.equals("updatePassword")) {
            salt = EncryptionUtil.generateSalt();
        } else if (acFlag.equals("update")) {
            salt = userInf.getSalt();
            if (salt == null || salt.trim().equals("")) {
                salt = EncryptionUtil.generateSalt();
            }
        }
        userInf.setSalt(salt);
        // 将加密后的密码设置到对象
        userInf.setPassword(EncryptionUtil.getEncryptedPassword(password, salt));
        String username = userInf.getUsername();
        if (username == null || username.trim().equals("")) {
            userInf.setUsername(loginName);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = new Date(System.currentTimeMillis());
        userInf.setCreatedate(simpleDateFormat.format(newDate));
        Integer roleId = userInf.getRoleId();
        if (roleId == null) {
            userInf.setRoleId(0);
            userInf.setRoleName("空");
        }
        return userInf;
    }
}
