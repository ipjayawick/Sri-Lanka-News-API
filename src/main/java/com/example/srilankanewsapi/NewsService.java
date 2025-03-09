package com.example.srilankanewsapi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NewsService {
    public HashMap<String, List> getAll(){
        HashMap<String, List> news = Scraper.scrapeNews();
        return  news;
    }
}
