package com.example.demo.service;

import com.example.demo.entity.RespBean;

/**
 * @author LiBo
 * @date 2023-03-03 16:51
 **/
public interface IAuthService {
    RespBean login(String loginname, String password);
}
