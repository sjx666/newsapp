package com.zxtt.newsapp.commons.entity;

import com.zxtt.newsapp.commons.dto.commons.AbstractBaseDomain;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;


@Data
@Table(name = "news")
public class News  extends AbstractBaseDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 新闻标题
     */
    private String title;

    /**
     * 发布时间
     */
    private Date createdate;

    /**
     * 新闻类型id
     */
    private Long typeid;

    /**
     * 点赞数量
     */
    @Column(name = "praiseCount")
    private Integer praisecount;

    /**
     * 回复数量
     */
    @Column(name = "replyCount")
    private Integer replycount;

    /**
     * 新闻内容
     */
    private String content;

}