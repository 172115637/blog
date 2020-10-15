package com.zjjhyzd.blog.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjjhyzd.blog.controller.base.BaseController;
import com.zjjhyzd.blog.entity.Article;
import com.zjjhyzd.blog.pojo.Page;
import com.zjjhyzd.blog.service.IArticleService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 李志祥
 * @since 2020-10-15
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    public ArticleController(HttpServletRequest request, HttpSession session) {
        super(request, session);
    }

    @PostMapping("/list")
    public Map<String, Object> list(@RequestBody Map<String, String> map) throws InvocationTargetException, IllegalAccessException {
        String typeId = map.get("typeId");
        String keyword = map.get("keyword");
        QueryWrapper<Article> wrapper = new QueryWrapper<>();

        Page<Article> page = new Page<>();
        BeanUtils.populate(page, map);
        if (Objects.nonNull(typeId)) {
            wrapper.eq("type_id", typeId);
        }
        if (Objects.nonNull(keyword)) {
            wrapper.like("title", keyword);
        }
        wrapper.eq("1", 1).select("article_id", "title", "create_time", "content", "type_id");

        Page<Article> articlePage = articleService.page(page, wrapper);
        return page(articlePage);
    }

    @PostMapping("/detail")
    public Map<String, Object> detail(@RequestBody Map<String, Long> map) {
        Long articleId = map.get("articleId");
        return success(articleService.getById(articleId));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    public Map<String, Object> save(@RequestBody Article article) {
        article.setCreateTime(null);
        articleService.saveOrUpdate(article);
        return success();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/del")
    public Map<String, Object> del(@RequestBody Map<String, Long> map) {
        Long articleId = map.get("articleId");
        articleService.removeById(articleId);
        return success();
    }
}
