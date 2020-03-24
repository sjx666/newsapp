package com.zxtt.newsapp.commons.entity;

import com.zxtt.newsapp.commons.dto.commons.AbstractBaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Table(name = "newstype")
public class Newstype extends AbstractBaseDomain implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 类型名称
     */
    private String typename;
}