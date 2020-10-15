package com.zjjhyzd.blog.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:/user.properties")
@ConfigurationProperties(prefix = "user")
public class UserProperties {
    private String username;
    private String password;
    private String nickname;
    private String headimg;
    private String autograph;
    private String githubUrl;
    private String weiboUrl;
    private String qrCodeUrl;
}
