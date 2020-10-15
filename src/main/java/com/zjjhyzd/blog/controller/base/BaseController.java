package com.zjjhyzd.blog.controller.base;

import com.zjjhyzd.blog.pojo.AjaxResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController extends AjaxResponse {
    private HttpServletRequest request;
    private HttpSession session;

    public BaseController(HttpServletRequest request, HttpSession session) {
        this.request = request;
        this.session = session;
    }
}