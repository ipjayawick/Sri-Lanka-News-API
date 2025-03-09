package com.example.srilankanewsapi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class NewsService {
    public HashMap<String, List> getAll(){
//        ArrayList<News> news=new ArrayList<>();
//        news.add(new News("BBC","alert","http"));
//        return  news;
        HashMap<String, List> news = Scraper.scrapeNews();
        return  news;
    }
}
