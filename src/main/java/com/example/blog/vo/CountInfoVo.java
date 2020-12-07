package com.example.blog.vo;

import lombok.Data;

/**
 * 文章统计信息
 */
@Data
public class CountInfoVo {

    /**
     * 文章总数
     */
    private Integer num;

    /**
     * 评论总数
     */
    private Integer commentNum;

    /**
     * 浏览总数
     */
    private Integer viewsNum;

    /**
     * 留言总数
     */
    private Integer messageNum;

}
