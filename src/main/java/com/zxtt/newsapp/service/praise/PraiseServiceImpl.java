package com.zxtt.newsapp.service.praise;

import com.zxtt.newsapp.commons.entity.Praise;
import com.zxtt.newsapp.commons.impl.BaseCrudServiceImpl;
import com.zxtt.newsapp.commons.mapper.PraiseMapper;
import org.springframework.stereotype.Service;


@Service
public class PraiseServiceImpl extends BaseCrudServiceImpl<Praise, PraiseMapper> implements PraiseService {
}
