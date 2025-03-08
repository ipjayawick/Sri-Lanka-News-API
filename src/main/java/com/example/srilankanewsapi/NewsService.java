package com.example.srilankanewsapi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService {
    public List<News> getAll(){
//        ArrayList<News> news=new ArrayList<>();
//        news.add(new News("BBC","alert","http"));
//        return  news;
        List<News> news = Scraper.scrapeNews();
        return  news;
    }
}
