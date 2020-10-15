package com.zjjhyzd.blog.controller;


import com.zjjhyzd.blog.controller.base.BaseController;
import com.zjjhyzd.blog.entity.ArticleType;
import com.zjjhyzd.blog.service.IArticleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李志祥
 * @since 2020-10-15
 */
@RestController
@RequestMapping("/article_type")
public class ArticleTypeController extends BaseController {

    public ArticleTypeController(HttpServletRequest request, HttpSession session) {
        super(request, session);
    }

    @Autowired
    private IArticleTypeService articleTypeService;

    @GetMapping("/list")
    public Map<String, Object> list() {
        return success(articleTypeService.list());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody ArticleType type) {
        articleTypeService.save(type);
        return success();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/del")
    public Map<String, Object> del(@RequestBody Map<String, Long> map) {
        Long typeId = map.get("typeId");
        articleTypeService.removeById(typeId);
        return success();
    }
}
