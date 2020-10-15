package com.zjjhyzd.blog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
@TableName(value = "t_article_type",autoResultMap = true)
public class ArticleType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_id", type = IdType.AUTO)
    private Long typeId;

    private String typeName;


}
