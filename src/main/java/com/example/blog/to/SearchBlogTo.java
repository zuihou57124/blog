package com.example.blog.to;

import lombok.Data;

/**
 * 查询博客条件vo
 */
@Data
public class SearchBlogTo {

    Integer recommend;

    String title;

    Integer typeId;

}
