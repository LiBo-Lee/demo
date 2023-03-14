package com.example.demo.common.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author LiBo
 * @date 2023-03-08 18:08
 **/

@Configuration
@EnableWebMvc
public class GlobalCorsConfig implements WebMvcConfigurer {
    /**
     * 注册拦截器
     * 	关于 Sa-Token的拦截器 和 自定义的拦截器，其实也可以只选其中一个的。
     *  我之所以两个都用了，是因为假如只用自带的拦截器的话，token续期只有调用 StpUtil 类里面的一些方法才会续期，但是我想要的是不管调用哪个接口，都自动续期。
     *  假如只用自定义的拦截器的话，它不能用注解鉴权，有试过用 extends SaInterceptor 也还是不行，所以只能两个拦截器一起用。
     * 可以根据自己实际情况选择其中一种或者两种都用。
     *
     * @param registry 注册实列
     * @author LiBo
     * @date 2023-03-09 10:19
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册Sa-Token的路由拦截器
        registry.addInterceptor(new SaInterceptor(handle -> {
            // 指定拦截的path列表且校验是否登录
            SaRouter.match("/auth/logout", "/user/**", "/dept/**", "/employee/**", "/document/**", "/notice/**")
                    .check(r -> StpUtil.checkLogin());
            // 权限校验 -- 不同模块认证不同权限
            SaRouter.match("/user/listUser", r -> StpUtil.checkPermission("user:view"));
            SaRouter.match("/user/insertUser", r -> StpUtil.checkPermission("user:add"));
            SaRouter.match("/user/updateUser", r -> StpUtil.checkPermission("user:update"));
            SaRouter.match("/user/deleteUser", r -> StpUtil.checkPermission("user:delete"));
            SaRouter.match("/dept/listDept", r -> StpUtil.checkPermission("dept:view"));
            SaRouter.match("/dept/insertDept", r -> StpUtil.checkPermission("dept:add"));
            SaRouter.match("/dept/updateDept", r -> StpUtil.checkPermission("dept:update"));
            SaRouter.match("/dept/deleteDeptByName", "/dept/deleteDept/**").check(r -> StpUtil.checkPermission("dept:delete"));
            SaRouter.match("/employee/listEmployee", r -> StpUtil.checkPermission("employee:view"));
            SaRouter.match("/employee/insertEmployee", r -> StpUtil.checkPermission("employee:add"));
            SaRouter.match("/employee/updateEmployee", r -> StpUtil.checkPermission("employee:update"));
            SaRouter.match("/employee/deleteEmployee", r -> StpUtil.checkPermission("employee:delete"));
            SaRouter.match("/document/listDocument", r -> StpUtil.checkPermission("document:view"));
            SaRouter.match("/document/insertDocument", r -> StpUtil.checkPermission("document:add"));
            SaRouter.match("/document/updateDocument", r -> StpUtil.checkPermission("document:update"));
            SaRouter.match("/document/deleteDocument", r -> StpUtil.checkPermission("document:delete"));
            SaRouter.match("/notice/listNotice", r -> StpUtil.checkPermission("notice:view"));
            SaRouter.match("/notice/insertNotice", r -> StpUtil.checkPermission("notice:add"));
            SaRouter.match("/notice/updateNotice", r -> StpUtil.checkPermission("notice:update"));
            SaRouter.match("/notice/deleteNotice", r -> StpUtil.checkPermission("notice:delete"));
        }
        )).addPathPatterns("/**");
        // 用于手动刷新Token的过期时间的拦截器
        registry.addInterceptor(new CustomIntercaptor()).addPathPatterns("/**")
                .excludePathPatterns("/auth/login");
    }
}
