package com.zxtt.newsapp.commons.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zxtt.newsapp.commons.dto.commons.AbstractBaseDomain;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Data
@Table(name = "news")
public class News  extends AbstractBaseDomain implements Serializable {
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
    private Integer praiseCount;

    /**
     * 回复数量
     */
    @Column(name = "replyCount")
    private Integer replyCount;

    /**
     * 新闻内容
     */
    private String content;
    private User user;
    private Files files;

}