package com.example.demo.common.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LiBo
 * @date 2023-03-10 16:55
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class ExceptionVo extends RuntimeException{
    private boolean result;
    private Integer status;
    private String msg;
    private Object data;

    public ExceptionVo(boolean result, Integer status, String msg) {
        this.result = result;
        this.status = status;
        this.msg = msg;
    }

    public ExceptionVo(boolean result, Integer status, String msg, Object data) {
        this.result = result;
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
}
