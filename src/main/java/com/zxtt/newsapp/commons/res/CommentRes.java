package com.zxtt.newsapp.commons.res;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Data
public class CommentRes implements Serializable {

    private Long id;

    /**
     * 新闻id
     */
    private Long newsid;

    private Long userid;

    /**
     * 内容
     */
    private String content;

    /**
     * 评论时间
     */
    private String jlDate;

    /**
     * 被点赞的数量
     */
    private Integer praisecount;


    /**
     * 昵称
     */
    private String name;


    /**
     * 用户图片
     */
    private String userImage;
}
