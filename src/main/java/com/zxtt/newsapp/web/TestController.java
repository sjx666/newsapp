package com.zxtt.newsapp.web;


import com.zxtt.newsapp.commons.entity.User;
import com.zxtt.newsapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {
    @Autowired
    private UserService userService;
    @GetMapping(value="genghuan/{h}")
    public Object show(@PathVariable(value="h")String h){
        List<User> list = userService.page(new User(), 0, 2).getList();
        return list;
    }
}
