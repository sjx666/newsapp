package com.zxtt.newsapp.commons.entity;

import com.zxtt.newsapp.commons.dto.commons.AbstractBaseDomain;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Data
@Table(name = "comment")
public class Comment extends AbstractBaseDomain implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date createdate;

    /**
     * 被点赞的数量
     */
    @Column(name = "praiseCount")
    private Integer praisecount;

}