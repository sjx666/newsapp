package com.zxtt.newsapp.service.news;

import com.zxtt.newsapp.commons.entity.News;
import com.zxtt.newsapp.commons.impl.BaseCrudServiceImpl;
import com.zxtt.newsapp.commons.mapper.NewsMapper;
import org.springframework.stereotype.Service;


@Service
public class NewsServiceImpl extends BaseCrudServiceImpl<News, NewsMapper> implements NewsService {
}
