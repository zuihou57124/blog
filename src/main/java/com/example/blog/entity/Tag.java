package com.example.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * tag
 * @author 
 */
@Data
public class Tag implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 标签名
     */
    private String tagname;

    private static final long serialVersionUID = 1L;
}