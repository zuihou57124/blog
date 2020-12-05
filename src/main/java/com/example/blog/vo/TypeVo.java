package com.example.blog.vo;



import lombok.Data;

import java.util.List;

/**
 * TypeVo
 */
@Data
public class TypeVo {

    /**
     * 分类id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String typeName;

    /**
     * 文章列表
     */
    private List<BlogVo> blogList;

}
