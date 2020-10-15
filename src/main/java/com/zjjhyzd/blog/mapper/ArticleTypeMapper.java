package com.zjjhyzd.blog.mapper;

import com.zjjhyzd.blog.entity.ArticleType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 李志祥
 * @since 2020-10-15
 */
public interface ArticleTypeMapper extends BaseMapper<ArticleType> {

    String selectNameById(@Param("typeId")Long typeId);
}
