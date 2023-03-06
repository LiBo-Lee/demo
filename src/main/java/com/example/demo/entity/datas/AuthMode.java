package com.example.demo.entity.datas;

import lombok.Data;

/**
 * @author LiBo
 * @date 2023-03-03 17:04
 **/
@Data
public class AuthMode {
    private String name;
    private String token;

    public AuthMode(String name, String token) {
        this.name = name;
        this.token = token;
    }
}
