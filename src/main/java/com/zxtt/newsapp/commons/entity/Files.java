package com.zxtt.newsapp.commons.entity;

import com.zxtt.newsapp.commons.dto.commons.AbstractBaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Table(name = "files")
public class Files extends AbstractBaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 所属作品
     */
    private Long newsid;

    /**
     * 文件路径
     */
    private String fileurl;

    /**
     * 0新闻 1评论 2用户头像 3主页轮播图
     */
    private Integer isflag;
}