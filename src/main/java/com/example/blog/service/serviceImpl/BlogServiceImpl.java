package com.example.blog.service.serviceImpl;

import com.example.blog.dao.BlogDao;
import com.example.blog.dao.TypeDao;
import com.example.blog.entity.Blog;
import com.example.blog.entity.Type;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
import com.example.blog.to.SearchBlogTo;
import com.example.blog.vo.BlogVo;
import com.example.blog.vo.TypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired(required = false)
    BlogDao blogDao;

    @Autowired
    TypeService typeService;

    /**
     * 添加类型
     */
    @Override
    public int add(Blog blog) {

        return blogDao.insert(blog);
    }

    /**
     * @param typeId
     * @return
     * 删除类型
     */
    @Override
    public int deleteById(Integer typeId) {

        return blogDao.deleteByPrimaryKey(typeId);
    }

    /**
     * 查询记录总数
     */
    @Override
    public int count(SearchBlogTo search) {

        return blogDao.count(search);
    }

    /**
     * 分页查询
     */
    @Override
    public List<BlogVo> selectList(int start, int offset, SearchBlogTo search) {

        return blogDao.selectList(start,offset,search)
                .stream()
                .map((blog -> {
                    BlogVo blogVo = new BlogVo();
                    BeanUtils.copyProperties(blog,blogVo);
                    Type type = typeService.selectById(blog.getTypeId());
                    TypeVo typeVo = new TypeVo();
                    BeanUtils.copyProperties(type,typeVo);
                    blogVo.setType(typeVo);

                    return blogVo;
                }))
                .collect(Collectors.toList());
    }

    /**
     * 分页查询
     */
    @Override
    public Blog selectById(Integer id) {

        return blogDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Blog blog) {

        return blogDao.updateByPrimaryKey(blog);
    }
}
