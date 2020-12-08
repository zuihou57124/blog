package com.example.blog.service.serviceImpl;

import com.example.blog.dao.TypeDao;
import com.example.blog.entity.Type;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
import com.example.blog.vo.BlogVo;
import com.example.blog.vo.TypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired(required = false)
    TypeDao typeDao;

    @Autowired
    BlogService blogService;
    /**
     * 添加类型
     */
    @Override
    public int add(Type type) {

        if(typeDao.selectByName(type.getTypeName())!=null){
            return 0;
        }

        return typeDao.insert(type);
    }

    /**
     * @param typeId
     * @return
     * 删除类型
     */
    @Override
    public int deleteById(Integer typeId) {

        return typeDao.deleteByPrimaryKey(typeId);
    }

    /**
     * 查询记录总数
     */
    @Override
    public int count() {

        return typeDao.count();
    }

    /**
     * 分页查询
     */
    @Override
    public List<TypeVo> selectList() {

        return typeDao.selectList(0,20)
                .stream()
                .map((type -> {
                    TypeVo typeVo = new TypeVo();
                    BeanUtils.copyProperties(type,typeVo);
                    List<BlogVo>blogVos = blogService.selectByTypeId(type,type.getId());
                    typeVo.setBlogList(blogVos);

                    return typeVo;
                }))
                .collect(Collectors.toList());
    }

    /**
     * 分页查询
     */
    @Override
    public List<Type> selectList(int start, int offset) {

        return typeDao.selectList(start,offset);
    }

    @Override
    public Type selectById(Integer id) {

        return typeDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateById(Type type) {

        return typeDao.updateByPrimaryKey(type);
    }
}
