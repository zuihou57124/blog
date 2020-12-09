package com.example.blog.service.serviceImpl;

import com.example.blog.dao.CommentDao;
import com.example.blog.entity.Comment;
import com.example.blog.service.CommentService;
import com.example.blog.vo.CommentVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {


    @Autowired(required = false)
    CommentDao commentDao;

    @Override
    public int insert(Comment comment) {

        comment.setCreatetime(new Date());

        return commentDao.insertSelective(comment);
    }

    /**
     * @param id
     * @return
     * 查询一级评论
     */
    @Override
    public List<CommentVo> selectListByBlogId(Integer id) {

        List<Comment> comments = commentDao.selectByBlogId(id);
        List<CommentVo> collect = comments.stream()
                .map((comment -> {
                    CommentVo commentVo = new CommentVo();
                    BeanUtils.copyProperties(comment, commentVo);
                    //查询每个一级评论的子评论
                    List<CommentVo> children = this.selectListByParentId(comment.getId());
                    commentVo.setCommentVoList(children);

                    return commentVo;
                }))
                .collect(Collectors.toList());

        return collect;
    }

    /**
     * @param id
     * @return
     * 查询子评论
     */
    @Override
    public List<CommentVo> selectListByParentId(Integer id) {

        List<Comment> comments = commentDao.selectByParentId(id);
        List<CommentVo> collect = comments.stream()
                .map((comment -> {
                    CommentVo commentVo = new CommentVo();
                    BeanUtils.copyProperties(comment, commentVo);

                    return commentVo;
                }))
                .collect(Collectors.toList());

        return collect;
    }

}
