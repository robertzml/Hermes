package com.shengdangjia.hermesauthentication.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Entity
@Table(name = "at_account")
public class Account {
    @Id
    public String id;

    public String userName;

    public String password;

    @NotEmpty(message = "电话不能为空")
    public String telephone;

    public String imei;

    public String realName;

    public String identityCard;

    public String email;

    @Column(name = "wx_openid")
    public String wxOpenId;

    public Timestamp registerTime;

    public int status;
}
