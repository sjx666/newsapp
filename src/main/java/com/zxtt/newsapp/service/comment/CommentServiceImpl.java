package com.zxtt.newsapp.service.comment;

import com.zxtt.newsapp.commons.entity.Comment;
import com.zxtt.newsapp.commons.impl.BaseCrudServiceImpl;
import com.zxtt.newsapp.commons.mapper.CommentMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl extends BaseCrudServiceImpl<Comment, CommentMapper> implements CommentService {
}
