package com.zjjhyzd.blog.controller;

import com.google.common.collect.Maps;
import com.zjjhyzd.blog.controller.base.BaseController;
import com.zjjhyzd.blog.service.impl.UserDetailsServiceImpl;
import com.zjjhyzd.blog.utils.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public class SecurityController extends BaseController {
    public SecurityController(HttpServletRequest request, HttpSession session) {
        super(request, session);
    }

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @PostMapping("/sign")
    public Map<String, Object> login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return error("用户名或者密码不能为空");
        }
        try {
            //使用用户名密码进行登录验证
            UsernamePasswordAuthenticationToken upToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            return error("用户名或者密码不正确");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        Map<String, Object> result = Maps.newHashMap();
        result.put("username", username);
        result.put("token", JwtUtil.sign(userDetails.getUsername(), userDetails.getAuthorities()));
        result.put("authorities", userDetails.getAuthorities());
        return success(result);
    }
}
