package com.shengdangjia.hermesauthentication.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 操作记录类
 */
@Entity
@Table(name = "au_action")
public class Action {
    @Id
    public String id;

    public String userId;

    public short type;

    public Timestamp logTime;

    public String parameter1;
}
