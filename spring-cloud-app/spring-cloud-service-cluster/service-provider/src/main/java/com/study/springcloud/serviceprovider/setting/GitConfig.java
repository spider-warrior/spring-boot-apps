package com.study.springcloud.serviceprovider.setting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @since 2020-12-30 11:01
 **/
@ConfigurationProperties(prefix = "application.root")
@Component
public class GitConfig {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "GitConfig{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
