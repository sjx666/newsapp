package com.zxtt.newsapp.service.news;

import com.zxtt.newsapp.commons.entity.News;
import com.zxtt.newsapp.commons.impl.BaseCrudServiceImpl;
import com.zxtt.newsapp.commons.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsServiceImpl extends BaseCrudServiceImpl<News, NewsMapper> implements NewsService {
   @Autowired
   private NewsMapper newsMapper;
    @Override
    public List<News> findnewsList() {
        return newsMapper.getNews();
    }
}
