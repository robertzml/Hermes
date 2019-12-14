package com.shengdangjia.common.model;

import java.time.LocalDateTime;

public class JwtState {
    /**
     * 验证结果
     */
    private boolean success;

    /**
     * 超期时间
     */
    private LocalDateTime expire;

    /**
     * 用户ID
     */
    private String uid;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public LocalDateTime getExpire() {
        return expire;
    }

    public void setExpire(LocalDateTime expire) {
        this.expire = expire;
    }
}
