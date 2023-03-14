package com.example.demo.common.config;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.strategy.SaStrategy;
import cn.dev33.satoken.util.SaFoxUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.common.util.RedisUtil;
import com.example.demo.common.base.BaseConstant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器。用于刷新Token的有效期
 *
 * @author LiBo
 * @date 2023-03-08 18:06
 **/
public class CustomIntercaptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader( "Set-Cookie" , "cookiename=httponlyTest;Path=/;Domain=domainvalue;Max-Age=seconds;HTTPOnly");
        response.setHeader( "Content-Security-Policy" , "default-src 'self'; script-src 'self'; frame-ancestors 'self'");
        response.setHeader("Access-Control-Allow-Origin", (request).getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Referrer-Policy","no-referrer");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        // 获取当前token（这个token获取的是请求头的token，也可以用 request 获取）
        String tokenValue = StpUtil.getTokenValue();
        // 根据token获取用户id（这里如果找不到id直接返回null，不会报错）
        String loginUserId = (String) StpUtil.getLoginIdByToken(tokenValue);
        // 判断token的创建时间是否大于2小时，如果是的话则需要刷新token
        long time = System.currentTimeMillis() - StpUtil.getSession().getCreateTime();
        long hour = time/1000/(60 * 60);
        if (hour>2){
            // 生成新的token有两种方式：
            // 方式一：先退出，然后再重新登录：退出之前得先把session中的用户信息拿出来，登录之后重新设置到session中。
            // 方式二：重新登录，并且重写token生成方式：重新token后，redis中以token值为key旧token还存在于redis中，得手动删除

            // 方式一：获取session中存储的用户信息，重新登录后，将这个用户信息重新设置到session中。
//            SysUser user = (SysUser) StpUtil.getSession().get("user");
//            StpUtil.logout(loginId); // 这里要生成新的token的话，要先退出再重新登录
//            StpUtil.login(loginId); // 然后再重新登录，生成新的token
//            String newToken = StpUtil.getTokenValue();
//            StpUtil.getSession().set("user",user);

            // 方式二：重新登录，并且重写token生成方式，并且把redis中旧token手动删除
            StpUtil.login(loginUserId);
            SaStrategy.me.createToken = (loginId, loginType) -> {
                return SaFoxUtil.getRandomString(32); // 生成新的token，随机32位长度字符串
            };
            String newToken = StpUtil.getTokenValue();
            RedisUtil redisUtil = SpringUtil.getBean(RedisUtil.class);
            redisUtil.del(BaseConstant.tokenCachePrefix+tokenValue);// 删除旧token
            response.setHeader(BaseConstant.tokenHeader, newToken);
        }
        long tokenTimeout = StpUtil.getTokenTimeout();// 获取过期时间
        //token没过期，过期时间不是-1的时候，每次请求都刷新过期时间
        if (tokenTimeout != -1){
            StpUtil.renewTimeout(3600);// 用于token续期
            StpUtil.updateLastActivityToNow();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
