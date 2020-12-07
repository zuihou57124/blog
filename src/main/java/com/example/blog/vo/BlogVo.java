package com.example.blog.vo;


import com.example.blog.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * BlogVo
 */
@Data
public class BlogVo {

    /**
     * 文章所属分类
     */
    private TypeVo type;

    /**
     * 文章所属用户id
     */
    private User user;

    /**
     * 文章的评论
     */
    private List<CommentVo> commentVoList;

    /**
     * 文章所属标签(可能有多个标签)
     */
    private List<TagVo> tagVoList;

    /**
     * 文章id
     */
    private Integer id;

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
     * 描述
     */
    private String description;

    /**
     * 创建日期
     */
    private Date createtime;

    /**
     * 更新日期
     */
    private Date updatetime;

}
