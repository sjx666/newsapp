package com.zxtt.newsapp.commons.entity;

import com.zxtt.newsapp.commons.dto.commons.AbstractBaseDomain;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;
@Data
@Table(name = "user")
public class User extends AbstractBaseDomain {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 昵称
     */
    private String name;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
     * 个人描述
     */
    private String description;

    /**
     * 被点赞的数量
     */
    @Column(name = "fondCount")
    private Integer fondcount;

    /**
     * 被关注的数量
     */
    @Column(name = "attentionCount")
    private Integer attentioncount;

    /**
     * 发布的文章数量
     */
    @Column(name = "articleCount")
    private Integer articlecount;

}