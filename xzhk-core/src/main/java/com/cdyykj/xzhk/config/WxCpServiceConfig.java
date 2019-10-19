package com.cdyykj.xzhk.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 企业微信工具
 */
@Configuration
@ConfigurationProperties(prefix = "wx.cp")
public class WxCpServiceConfig implements Serializable {

    private  String corpid;
    private  String corpsecret;
    private String token;
    private String aesKey;

    public String getCorpid() {
        return corpid;
    }

    public void setCorpid(String corpid) {
        this.corpid = corpid;
    }

    public String getCorpsecret() {
        return corpsecret;
    }

    public void setCorpsecret(String corpsecret) {
        this.corpsecret = corpsecret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }
}
