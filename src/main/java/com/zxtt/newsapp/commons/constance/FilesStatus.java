package com.zxtt.newsapp.commons.constance;

public enum FilesStatus {
    newsFiles("新闻",0),
    commentFiles("评论",1),
    userFiles("用户",2),
    HomeFiles("主页轮播图",3);
    private String name;
    private int i;

    private FilesStatus(String name, int i) {
        this.name = name;
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public int getI() {
        return i;
    }
}
