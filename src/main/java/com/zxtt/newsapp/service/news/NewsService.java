package com.zxtt.newsapp.service.news;

import com.zxtt.newsapp.commons.BaseCrudService;
import com.zxtt.newsapp.commons.entity.News;

import java.util.List;

public interface NewsService extends BaseCrudService<News> {
    List<News> findnewsList();

    List<News> selectNewsList();

    int deleteFreshNews(Long id);//根据id删除新鲜事

    int updateFreshNews(News news);//修改

    int addFreshNews(News news);//删除

    News selectOneById(Long id);
}
