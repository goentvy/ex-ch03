package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {
    // 5-2-1 첫번째 컨트롤러
    @RequestMapping("/")
    public String hello() {
        return "Hello <strong>Backend</strong>";
    }

    // 5-2-2 첫번째 컨트롤러
    @RequestMapping("/bye")
    public String bye() {
        return "Bye";
    }

    // http://localhost:8080/article?title=스트링부트&content=완성하기
    @RequestMapping(value="/article", method= RequestMethod.GET)
    public String createArticle(@RequestParam("title") String title, @RequestParam("content") String content) {
        return String.format("title = %s / content = %s", title, content);
    }
}
