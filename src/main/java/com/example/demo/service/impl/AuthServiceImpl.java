package com.example.demo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.base.BaseConstant;
import com.example.demo.common.util.EncryptionUtil;
import com.example.demo.common.util.RedisUtil;
import com.example.demo.common.vo.ExceptionVo;
import com.example.demo.entity.RespBean;
import com.example.demo.entity.UserInf;
import com.example.demo.entity.datas.AuthMode;
import com.example.demo.mapper.UserInfMapper;
import com.example.demo.service.IAuthService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.*;

/**
 * @author LiBo
 * @date 2023-03-03 16:52
 **/

@Service
public class AuthServiceImpl implements IAuthService {
    @Resource
    private UserInfMapper userInfMapper;

    @Resource
    private RedisUtil redisUtil;

    /** 密码最大错误次数 */
    private int ERROR_COUNT = 3;

    @Override
    public RespBean login(String loginname, String password) {
        if (loginname == null || loginname.trim().equals("")) {
            return RespBean.error("请提供用户登录名！");
        }
        try {
            UserInf userInf = passwordErrNum(loginname, password);
            // 先查看当前登录名是否存在旧的Token，如果有则退出登录
            String oldToken = StpUtil.getTokenValueByLoginId(userInf.getLoginname());
            if (StrUtil.isNotBlank(oldToken)) {
                StpUtil.logout(userInf.getLoginname());
            }
            StpUtil.login(loginname);
            String token = StpUtil.getTokenValue();
            StpUtil.getSession().set("userInf", userInf);
            return RespBean.ok("登录成功！", new AuthMode(loginname, token));
        } catch (ExceptionVo e) {
            return RespBean.error(e.getMsg(), e.getData());
        } catch (Exception e) {
            return RespBean.error(e.getMessage());
        }
    }

    private UserInf passwordErrNum(String loginName, String password) {
        //查询用户
        QueryWrapper<UserInf> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(UserInf::getLoginname, loginName);
        UserInf userInf = userInfMapper.selectOne(queryWrapper);
        if (userInf == null){
            throw new ExceptionVo(false, 500,"该用户登录名（" + loginName + "）不存在！");
        }
        //根据前端输入的密码（明文），和加密的密码、盐值进行比较，判断输入的密码是否正确
        boolean authenticate = EncryptionUtil.authenticate(password, userInf.getPassword(), userInf.getSalt());
        if (authenticate) {
            //密码正确错误次数清零
            redisUtil.del(BaseConstant.ERROR_COUNT+loginName);
        } else {
            long currentTime = System.currentTimeMillis();
            //判断账号是否锁定
            boolean flag = lockedUser(currentTime, loginName);

            //错误3次，锁定15分钟后才可登陆 允许时间加上定义的登陆时间（毫秒）
            String str = "15";
            long timeStamp = System.currentTimeMillis()+900000;
            //密码登录限制（1：连续错3次，锁定账号15分钟。2：连续错5次，锁定账号30分钟）
            if (userInf.getPwdLoginLimit()==2){
                ERROR_COUNT = 5;
                str = "30";
                timeStamp = System.currentTimeMillis()+1800000;
            }
            //密码登录限制（1：连续错3次，锁定账号15分钟。2：连续错5次，锁定账号30分钟）
            if (redisUtil.hasKey(BaseConstant.ERROR_COUNT+loginName)){
                int i = Integer.parseInt(redisUtil.hget(BaseConstant.ERROR_COUNT+loginName,"errorNum").toString());
                if (flag && i==ERROR_COUNT){
                    redisUtil.hset(BaseConstant.ERROR_COUNT+loginName,"errorNum",1);
                }else {
                    redisUtil.hincr(BaseConstant.ERROR_COUNT+loginName,"errorNum",1);
                }
                redisUtil.hset(BaseConstant.ERROR_COUNT+loginName,"loginTime",timeStamp);
            }else {
                Map<String,Object> map = new HashMap<>();
                map.put("errorNum",1);
                map.put("loginTime",timeStamp);
                redisUtil.hmset(BaseConstant.ERROR_COUNT+loginName, map, -1);
            }
            int i = Integer.parseInt(redisUtil.hget(BaseConstant.ERROR_COUNT+loginName,"errorNum").toString());
            if (i==ERROR_COUNT){
                throw new ExceptionVo(false, 500,"您的密码已错误"+ERROR_COUNT+"次，现已被锁定，请"+str+"分钟后再尝试");
            }
            throw new ExceptionVo(false, 500,"密码错误，总登录次数"+ERROR_COUNT+"次，剩余次数: " + (ERROR_COUNT-i));
        }
        List<String> collect = new ArrayList<>();
        collect.add(userInf.getRoleId().toString());
        userInf.setRoleIds(collect.toArray(new String[0]));
        return userInf;
    }

    private boolean lockedUser(long currentTime, String loginname) {
        boolean flag = false;
        if (redisUtil.hasKey(BaseConstant.ERROR_COUNT+loginname)) {
            long longTime = Long.parseLong(redisUtil.hget(BaseConstant.ERROR_COUNT+loginname, "loginTime").toString());
            int i = Integer.parseInt(redisUtil.hget(BaseConstant.ERROR_COUNT+loginname, "errorNum").toString());
            if (i >= ERROR_COUNT && currentTime < longTime) {
                Duration between = LocalDateTimeUtil.between(LocalDateTimeUtil.of(currentTime), LocalDateTimeUtil.of(longTime));
                throw new ExceptionVo(false, 500, "账号锁定中，还没到允许登录的时间，请"+between.toMinutes()+"分钟后再尝试");
            } else {
                flag = true;
            }
        }
        return flag;
    }

    @Override
    public RespBean logout(String loginname, String token) {
        if (loginname == null || loginname.trim().equals("")) {
            return RespBean.error("请提供用户登录名！");
        }
        if (token == null || token.trim().equals("")) {
            return RespBean.error("请提供用户令牌！");
        }
        StpUtil.logoutByTokenValue(token);
        return RespBean.ok("登出成功！");
    }
}