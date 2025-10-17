package com.example.demo.controller;

import com.example.demo.dto.ArticleRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyForm {
//  http://localhost:8080/article-get?title=제목칸&content=내용칸
    @GetMapping("/article-get")
    public String articleGet(@RequestParam String title, @RequestParam String content) {
        String result = "제목: " + title + "<br>내용: " + content;
        return result;
    }
//  http://localhost:8080/article-post
    @PostMapping("/article-post")
    public String ariticlePost(@ModelAttribute ArticleRequest request) {
        String result = "제목: " + request.title() + "<br>내용: " + request.content();
        return result;
    }
//  http://localhost:8080/article-postman
    @PostMapping("/article-postman")
    public String ariticlePostman(@RequestBody ArticleRequest request) {
        String result = "제목: " + request.title() + "<br>내용: " + request.content();
        return result;
    }
//  http://localhost:8080/article-auto
    @PostMapping("/article-auto")
    public String ariticleAuto(ArticleRequest request) {
        String result = "제목: " + request.title() + "<br>내용: " + request.content();
        return result;
    }
}
