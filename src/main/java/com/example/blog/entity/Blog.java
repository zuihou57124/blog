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

    private static final long serialVersionUID = 1L;
}