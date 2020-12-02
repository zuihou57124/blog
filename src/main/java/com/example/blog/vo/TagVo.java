package com.example.blog.vo;

import com.example.blog.entity.Blog;
import lombok.Data;

import java.util.List;

/**
 * TagVo
 */
@Data
public class TagVo {

    /**
     * 标签id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagname;

    /**
     * 标签的文章列表
     */
    private List<BlogVo> blogVoList;

}
