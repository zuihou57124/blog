package com.example.blog.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class Comment implements Serializable {
    /**
     * 主键id
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

    private static final long serialVersionUID = 1L;
}