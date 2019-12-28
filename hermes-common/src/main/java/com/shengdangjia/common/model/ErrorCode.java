package com.shengdangjia.common.model;

/**
 * 错误码
 */
public enum ErrorCode {
    SUCCESS("success", 0),
    ERROR("error", 1),
    EXCEPTION("exception", 2),
    NOT_IMPLEMENT("not implement ", 3),
    OBJECT_DELETED("object deleted", 4),
    OBJECT_NOT_FOUND("object not found ", 5),
    DATABASE_FAILED("database failed", 6),
    NETWORK_ERROR("network error", 7),
    NEED_AUTHORIZATION("need authorization", 8),
    AUTHORIZATION_FAILED("authorization failed", 9),
    AUTHORIZATION_EXPIRE("authorization expire", 10);

    private String errorMessage;

    private int code;

    private ErrorCode(String errorMessage, int code) {
        this.setErrorMessage(errorMessage);
        this.setCode(code);
    }

    public static String getErrorMessage(int code) {
        for (ErrorCode e : ErrorCode.values()) {
            if (e.getCode() == code) {
                return e.errorMessage;
            }
        }

        return "";
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
