package com.example.demo.common.config;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.NotSafeException;
import com.example.demo.entity.RespBean;
//import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;

/**
 * @author LiBo
 * @date 2023-03-09 09:58
 **/

@Slf4j
@RestControllerAdvice
public class GlobalExceptionConfig {

    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public RespBean bindExceptionHandler(BindException e) {
        log.error(e.getMessage());
        StringBuilder sb = new StringBuilder();
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            for (int i = 0; i < bindingResult.getAllErrors().size(); i++) {
                ObjectError error = bindingResult.getAllErrors().get(i);
                sb.append((i == 0 ? "" : "\n") + error.getDefaultMessage());
            }
        }
        return RespBean.error(sb.toString());
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public RespBean handler(ConstraintViolationException e) {
        log.error(e.getMessage());
        StringBuffer sb = new StringBuffer();
        int i = 0;
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            sb.append((++i == 1 ? "" : "\n") + violation.getMessage());
        }
        return RespBean.error(sb.toString());
    }

    // 拦截：请求方式异常
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public RespBean httpReqMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        log.error("请求方式不支持！其他：{}", e.getMessage());
        return RespBean.error("请求方式不支持！");
    }

    // 拦截：未登录异常
    @ExceptionHandler(value = NotLoginException.class)
    public RespBean notLoginException(NotLoginException e) {
        log.error("用户未登录！其他：{}", e.getMessage());
        return RespBean.error("用户未登录！");
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(value = NotPermissionException.class)
    public RespBean notPermissionException(NotPermissionException e) {
        log.error("用户无权限！其他：{}", e.getMessage());
        return RespBean.error("用户无权限！");
    }

    // 拦截：缺少角色异常
    @ExceptionHandler(value = NotRoleException.class)
    public RespBean notRoleException(NotRoleException e) {
        log.error("用户无角色！其他：{}", e.getMessage());
        return RespBean.error("用户无角色！");
    }

    // 拦截：二级认证校验失败异常
    @ExceptionHandler(value = NotSafeException.class)
    public RespBean notSafeException(NotSafeException e) {
        log.error("用户二级认证校验失败！其他：{}", e.getMessage());
        return RespBean.error("用户二级认证校验失败！");
    }

    // 拦截：其他异常
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Exception.class)
    public RespBean exception(Exception e) {
        String msg = "未知异常！";
        if (e instanceof NullPointerException) {
            msg = "空指针异常！";
        } else if (e instanceof SQLException) {
            msg = "SQL异常！";
        }
        log.error(msg + "其他：{}", e.getMessage());
        e.printStackTrace();
        return RespBean.error(msg);
    }
}
