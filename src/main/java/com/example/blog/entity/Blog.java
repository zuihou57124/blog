package com.example.blog.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * blog
 * @author 
 */
@Data
public class Blog implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    private Integer typeId;

    private Integer userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 首图
     */
    private String firstpicture;

    /**
     * 标记
     */
    private String flag;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 是否开启赞赏
     */
    private Boolean appreciation;

    /**
     * 是否可以转载
     */
    private Boolean share;

    /**
     * 是否发布(草稿)
     */
    private Boolean published;

    /**
     * 是否推荐
     */
    private Boolean recommend;

    /**
     * 创建日期
     */
    private Date createtime;

    /**
     * 更新日期
     */
    private Date updatetime;

    /**
     * 描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}