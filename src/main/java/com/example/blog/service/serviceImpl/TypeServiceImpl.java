package com.example.blog.service.serviceImpl;

import com.example.blog.dao.TypeDao;
import com.example.blog.entity.Type;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired(required = false)
    TypeDao typeDao;

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
