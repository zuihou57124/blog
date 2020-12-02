package com.example.blog.vo;


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
    private Integer userId;

    /**
     * 文章的评论
     */
    private List<CommentVo> commentVoList;

    /**
     * 文章所属标签
     */
    private TagVo tagVo;

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
    private Byte appreciation;

    /**
     * 是否可以转载
     */
    private Byte share;

    /**
     * 是否发布(草稿)
     */
    private Byte published;

    /**
     * 是否推荐
     */
    private Byte recommend;

    /**
     * 创建日期
     */
    private Date createtime;

    /**
     * 更新日期
     */
    private Date updatetime;

}
