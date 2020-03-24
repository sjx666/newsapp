package com.zxtt.newsapp.commons.mapper;

import com.tk.mybatis.MyMapper;
import com.zxtt.newsapp.commons.entity.News;

import java.util.List;

public interface NewsMapper extends MyMapper<News> {
    List<News> getNews();

    List<News> getNewList(); //获取所有新鲜事

    int delFreshNews(Long id);//根据id删除新鲜事

    int updFreshNews(News news);//修改

    int adFreshNews(News news);//这个？
    News selectOneById(Long id);
}