package com.zxtt.newsapp.commons.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tk.mybatis.MyMapper;
import com.zxtt.newsapp.commons.BaseCrudService;
import com.zxtt.newsapp.commons.dto.commons.AbstractBaseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;

@Transactional(readOnly = true)
public class BaseCrudServiceImpl<T extends AbstractBaseDomain,M extends MyMapper<T>> implements BaseCrudService<T> {
    @Autowired(required = false)
    protected  M mapper;

    // 获取泛型的class
    private Class<T> entityClass=(Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    @Transactional(readOnly = false)
    public T insert(T domain) {

       return mapper.insertUseGeneratedKeys(domain)>0?domain:null;
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T domain) {
        return mapper.deleteByPrimaryKey(domain);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T domain) {
        return mapper.updateByPrimaryKeySelective(domain);
    }

    @Override
    public int count(T t) {
        return mapper.selectCount(t);
    }

    @Override
    public T selectOne(T t) {
        return mapper.selectOne(t);
    }

    @Override
    public PageInfo<T> page(T domain, int pageNo, int pageSize) {
        /*Example example = new Example(entityClass);
        example.createCriteria().andEqualTo(domain);
        PageHelper.startPage(pageNo,pageSize);
        PageInfo<T> pageInfo = new PageInfo<>(mapper.selectByExample(example));
        return pageInfo;*/
        return null;
    }
}
