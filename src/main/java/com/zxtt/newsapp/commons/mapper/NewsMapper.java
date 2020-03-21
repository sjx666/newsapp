package com.zxtt.newsapp.commons.mapper;

import com.tk.mybatis.MyMapper;
import com.zxtt.newsapp.commons.entity.News;

import java.util.List;

public interface NewsMapper extends MyMapper<News> {
    List<News> getNews();
}