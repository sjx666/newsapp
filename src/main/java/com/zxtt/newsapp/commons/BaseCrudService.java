package com.zxtt.newsapp.commons;

import com.github.pagehelper.PageInfo;
import com.zxtt.newsapp.commons.dto.commons.AbstractBaseDomain;

import java.util.List;

/**
 *   通用的业务逻辑
 */
public interface BaseCrudService<T extends AbstractBaseDomain> {

    T insert(T domain);

    int delete(T domain);

    int update(T domain);

    int count(T t);

    T selectOne(T t);

    PageInfo<T> page(T t, int pageNo, int pageSize);
}
