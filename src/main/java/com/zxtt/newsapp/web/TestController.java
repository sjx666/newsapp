package com.zxtt.newsapp.web;


import com.zxtt.newsapp.commons.entity.User;
import com.zxtt.newsapp.commons.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping(value="genghuan/{h}")
    public Object show(@PathVariable(value="h")String h){
        List<User> page = userMapper.selectAll();
        return page;
    }
}
