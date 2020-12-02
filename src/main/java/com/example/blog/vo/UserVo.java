package com.example.blog.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class UserVo {

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户发表的文章
     */
    private List<BlogVo> blogVoList;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 头像
     */
    private String picture;

    /**
     * 用户类型
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 更新时间
     */
    private Date updatetime;

}
