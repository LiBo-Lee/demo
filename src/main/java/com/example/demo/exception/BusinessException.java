package com.example.demo.exception;

public class BusinessException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private static String createErrMsg(String msgBody) {
        return msgBody;
    }

    public BusinessException(String sMessage) {
        super(createErrMsg(sMessage));
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String sMessage, Throwable throwable) {
        super(createErrMsg(sMessage), throwable);
    }
}
