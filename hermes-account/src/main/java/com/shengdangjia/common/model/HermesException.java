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

    public int getCode() {
        return code;
    }
}
