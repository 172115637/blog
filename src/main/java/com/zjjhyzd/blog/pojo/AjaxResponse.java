package com.zjjhyzd.blog.pojo;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.HashMap;
import java.util.Map;

public class AjaxResponse {
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    private static Map<String, Object> generate(int code, String status) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", code);
        result.put("status", status);
        return result;
    }

    private static Map<String, Object> generate(int code, String status, String message) {
        Map<String, Object> result = generate(code, status);
        result.put("msg", message);
        return result;
    }

    private static Map<String, Object> generate(int code, String status, Object data) {
        Map<String, Object> result = generate(code, status);
        result.put("data", data);
        return result;
    }

    public static Map<String, Object> success() {
        return generate(0, SUCCESS);
    }

    public static Map<String, Object> success(Object data) {
        return generate(0, SUCCESS, data);
    }

    public static Map<String, Object> error(String message) {
        return generate(1, ERROR, message);
    }

    public static Map<String, Object> error() {
        return generate(1, ERROR);
    }

    public static Map<String, Object> error(Integer code, String message) {
        return generate(code, ERROR, message);
    }

    public static Map<String, Object> page(Long count, Object data, Long page, Long limit) {
        Map<String, Object> result = generate(0, SUCCESS);
        result.put("data", data);
        result.put("count", count);
        result.put("page", page);
        result.put("limit", limit);
        return result;
    }

    public static Map<String, Object> page(IPage<?> page) {
        return page(page.getTotal(), page.getRecords(), page.getCurrent(), page.getSize());
    }
}
