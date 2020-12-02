package com.example.blog.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommentVo {

    //评论所属文章id
    private Integer blogId;

    /**
     * 评论id
     */
    private Integer id;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像url
     */
    private String picture;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createtime;
}
