package com.example.mualakabashi_simplenewsapi.controllers;


import com.example.mualakabashi_simplenewsapi.dto.newsDto.NewsDto;
import com.example.mualakabashi_simplenewsapi.dto.newsDto.NewsRequestDto;
import com.example.mualakabashi_simplenewsapi.models.News;
import com.example.mualakabashi_simplenewsapi.models.ResponseModel;
import com.example.mualakabashi_simplenewsapi.services.NewsService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/news")
    public ResponseEntity<ResponseModel<List<NewsDto>>> getNews() {
        return ResponseModel.onSuccess(newsService.getNews(), 200);
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<ResponseModel<News>> getNewsById(@PathVariable int id) {
        return ResponseModel.onSuccess(newsService.getNewsById(id),200);
    }

    @PostMapping("/addNews")
    public ResponseEntity<ResponseModel<String>> addNews(HttpServletRequest request, @RequestBody NewsRequestDto newsRequestDto) {
        return ResponseModel.onSuccess(newsService.addNews(request,newsRequestDto),200);
    }

    @PutMapping("/editNews/{id}")
    public ResponseEntity<ResponseModel<String>> editNews(HttpServletRequest request,@RequestBody NewsRequestDto newsRequestDto, @PathVariable int id) {
        return ResponseModel.onSuccess(newsService.editNews(request,newsRequestDto, id),201);
    }

    @DeleteMapping("deleteNews/{id}")
    public ResponseEntity<ResponseModel<String>> deleteNews(@PathVariable int id) {
        return ResponseModel.onSuccess(newsService.deleteNews(id),200);
    }


}
