package com.shengdangjia.common.model;

import java.time.LocalDateTime;

public class JwtState {
    /**
     * 验证结果
     */
    private boolean success;

    private boolean expire;

    /**
     * 超期时间
     */
    private LocalDateTime expireTime;

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

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 是否超时
     */
    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }
}
