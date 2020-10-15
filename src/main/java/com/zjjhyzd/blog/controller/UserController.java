package com.zjjhyzd.blog.controller;

import com.zjjhyzd.blog.controller.base.BaseController;
import com.zjjhyzd.blog.entity.UserProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

@RestController
public class UserController extends BaseController {


    public UserController(HttpServletRequest request, HttpSession session) {
        super(request, session);
    }

    @Autowired
    private UserProperties userProperties;

    @GetMapping("/user/detail")
    public Map<String, Object> detail() {
        return success(userProperties);
    }

    @GetMapping("/is_admin")
    public Map<String, Object> isAdmin() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(name);
        return success(Objects.nonNull(name) && name.equals(userProperties.getUsername()));
    }
}
