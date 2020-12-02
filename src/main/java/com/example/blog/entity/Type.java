package com.example.blog.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * type
 * @author 
 */
@Data
public class Type implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 分类名称
     */
    private String typename;

    private static final long serialVersionUID = 1L;
}