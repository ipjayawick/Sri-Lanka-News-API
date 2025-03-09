package com.example.srilankanewsapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
class NewsController {

    private final NewsService newsService;

    @Autowired
    NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/all")
    HashMap<String, List> all() {
        return newsService.getAll();
    }

}