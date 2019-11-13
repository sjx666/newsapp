package com.zxtt.newsapp.service.files;

import com.zxtt.newsapp.commons.entity.Files;
import com.zxtt.newsapp.commons.impl.BaseCrudServiceImpl;
import com.zxtt.newsapp.commons.mapper.FilesMapper;
import org.springframework.stereotype.Service;


@Service
public class FilesServiceImpl extends BaseCrudServiceImpl<Files, FilesMapper> implements FilesService {
}
