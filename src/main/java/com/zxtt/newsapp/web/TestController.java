package com.zxtt.newsapp.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value="genghuan/{h}")
    public String show(@PathVariable(value="h")String h){
        return h;
    }
}
