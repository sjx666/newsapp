package com.zxtt.newsapp.service.news;

import com.zxtt.newsapp.commons.BaseCrudService;
import com.zxtt.newsapp.commons.entity.News;

import java.util.List;

public interface NewsService extends BaseCrudService<News> {
    List<News> findnewsList();
}
