package com.zxtt.newsapp.service.newstype;

import com.zxtt.newsapp.commons.entity.Newstype;
import com.zxtt.newsapp.commons.impl.BaseCrudServiceImpl;
import com.zxtt.newsapp.commons.mapper.NewstypeMapper;
import org.springframework.stereotype.Service;


@Service
public class NewstypeServiceImpl extends BaseCrudServiceImpl<Newstype, NewstypeMapper> implements NewstypeService {
}
