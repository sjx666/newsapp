package com.zxtt.newsapp.commons.enums;



public enum FileEnum {

    news(0,"新闻"),
    comment(1,"评论"),
    userImageUrl(2,"用户头像"),
    home(3,"主页轮播图");
    private Integer result;
    private String mes;
    FileEnum(Integer result,String mes){
        this.result=result;
        this.mes=mes;
    }

    public Integer getResult(){
        return this.result;
    }
}
