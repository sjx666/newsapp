package com.zxtt.newsapp.web.controller;

import com.zxtt.newsapp.service.comment.CommentService;
import com.zxtt.newsapp.service.files.FilesService;
import com.zxtt.newsapp.service.praise.PraiseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 新闻控制业务
 */
public class NewsController {


    @Autowired
    private CommentService commentService;


    @Autowired
    private UserController userController;

    @Autowired
    private PraiseService praiseService;

    @Autowired
    private FilesService filesService;


}