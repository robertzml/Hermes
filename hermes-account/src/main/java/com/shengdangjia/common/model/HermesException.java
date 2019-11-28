package com.shengdangjia.common.model;

/**
 * 自定义异常
 */
public class HermesException extends Exception {
    private int code;

    public HermesException(int code, String message) {
        super(message);
        this.code = code;
    }

    public HermesException(ErrorCode errorCode) {
        super(errorCode.getErrorMessage());
        this.code = errorCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
