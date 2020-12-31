package com.study.springcloud.serviceprovider.controller;

import com.study.springcloud.serviceprovider.setting.GitConfig;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version V1.0
 * @since 2020-12-30 10:56
 **/
@RefreshScope
@RequestMapping("config")
@RestController
public class ConfigController {

    private final GitConfig gitConfig;

    @GetMapping
    public GitConfig gitConfig() {
        return gitConfig;
    }

    public ConfigController(GitConfig gitConfig) {
        this.gitConfig = gitConfig;
    }
}
