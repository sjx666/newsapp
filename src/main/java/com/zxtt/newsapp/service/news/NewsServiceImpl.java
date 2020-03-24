package com.zxtt.newsapp.service.news;

import com.zxtt.newsapp.commons.entity.News;
import com.zxtt.newsapp.commons.impl.BaseCrudServiceImpl;
import com.zxtt.newsapp.commons.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class NewsServiceImpl extends BaseCrudServiceImpl<News, NewsMapper> implements NewsService {
   @Autowired
   private NewsMapper newsMapper;
    @Override
    public List<News> findnewsList() {
        return newsMapper.getNews();
    }

    @Override
    public List<News> selectNewsList() {
        return newsMapper.getNewList();
    }

    @Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public int deleteFreshNews(Long id) {
        return newsMapper.delFreshNews(id);
    }
    @Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public int updateFreshNews(News news) {
        return newsMapper.updFreshNews(news);
    }
    @Transactional(readOnly = false, isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
    @Override
    public int addFreshNews(News news) {
        return newsMapper.insert(news);

    }

    @Override
    public News selectOneById(Long id) {
        return newsMapper.selectOneById(id);
    }
}
