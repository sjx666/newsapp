package com.zxtt.newsapp.commons.entity;

import com.zxtt.newsapp.commons.dto.commons.AbstractBaseDomain;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Data
@Table(name = "praise")
public class Praise extends AbstractBaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 新闻id
     */
    private Long newsid;

    /**
     * 点赞时间
     */
    private Date createdate;

    /**
     * 是新闻还是评论 0新闻 1是评论
     */
    private Integer isflag;

    /**
     * 点赞用户id
     */
    private Long userid;
}