package com.example.blog.utils;


import lombok.Data;

import java.util.List;

/**
 * 分页工具类
 */
@Data
public class PageUtils {

    //总记录数
    Integer num;

    //总页数
    Integer total;

    //当前页
    Integer current;

    //每页显示记录数
    Integer limit;

    //开始位置
    Integer start;

    //偏移量
    Integer offset;

    //记录
    List<?> list;

}
