package com.zjjhyzd.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author 李志祥
 * @since 2020-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "t_article", autoResultMap = true, resultMap = "BaseResultMap")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "article_id", type = IdType.AUTO)
    private Long articleId;

    private String title;

    private Long typeId;

    private String content;

    private Date createTime;

    @TableField(exist = false)
    private String typeName;
}
