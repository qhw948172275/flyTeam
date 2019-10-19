package com.cdyykj.commons;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 设置文件名前缀
 */
@Configuration
@ConfigurationProperties(prefix = "folder.out")
public class OutPutFolderConfig implements Serializable {
    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
