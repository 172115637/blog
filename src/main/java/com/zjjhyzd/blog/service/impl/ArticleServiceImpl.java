package com.zjjhyzd.blog.service.impl;

import com.zjjhyzd.blog.entity.Article;
import com.zjjhyzd.blog.mapper.ArticleMapper;
import com.zjjhyzd.blog.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 李志祥
 * @since 2020-10-15
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

}
