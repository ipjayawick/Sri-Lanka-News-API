package com.example.srilankanewsapi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scraper {
    public static HashMap<String, List> scrapeNews() {
        HashMap<String, List> newsMap = new HashMap<>();

        try {
            List<News> lankadeepaNews = new ArrayList<>();
            // Fetch the HTML content
            String url = "https://www.lankadeepa.lk/latest_news/1";
            Document document = Jsoup.connect(url).get();

            Element latestSection = document.selectFirst("section.bg0.p-t-10.p-b-0");

            if (latestSection != null) {
                Elements postDivs = latestSection.select("div.flex-wr-sb-s.p-t-20.p-b-15.how-bor2.row");

                for (Element postDiv : postDivs) {
                    String source = postDiv.selectFirst("a").attr("href");
                    String imageUrl= postDiv.selectFirst("a").selectFirst("img").attr("src");

                    Element col7Div = postDiv.selectFirst(".col-7");
                    if (col7Div != null) {
                        Elements h5Tags = col7Div.select("h5");
                        String topic = h5Tags.first() != null ? h5Tags.first().text().trim() : "";
                        String description = h5Tags.last() != null ? h5Tags.last().text().trim() : "";

                        lankadeepaNews.add(new News(topic, description,source,imageUrl));
                    }
                }
                newsMap.put("lankadeepa",lankadeepaNews);
            }
        } catch (IOException e) {
            System.err.println("Error fetching page: " + e.getMessage());
        }

        try {
            List<News> bbcNews = new ArrayList<>();
            String url = "https://www.bbc.com/sinhala/topics/cg7267dz901t";
            Document document = Jsoup.connect(url).get();

            Elements titleElements = document.select(".bbc-6e44zt.e47bds20");

            Elements dateElements = document.select(".promo-timestamp.bbc-11oryzm.e1mklfmt0");

            Elements imageElements = document.select(".bbc-139onq");

            int size = Math.min(titleElements.size(), dateElements.size());
            for (int i = 0; i < size; i++) {
                Element titleElement = titleElements.get(i);
                Element dateElement = dateElements.get(i);
                Element imageElement=imageElements.get(i);

                String title = titleElement.text();
                String source = titleElement.selectFirst("a") != null ? titleElement.selectFirst("a").attr("href") : "#";
                String date = dateElement.text();
                String imageUrl=imageElement.attr("src");

                bbcNews.add(new News(title,"",source,imageUrl));
            }
            newsMap.put("bbc",bbcNews);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<News> adaDeranaNews = new ArrayList<>();
            String url = "https://sinhala.adaderana.lk/";
            Document document = Jsoup.connect(url).get();

            Elements elements=document.select("div.hot-news.news-story");

            for (Element element : elements) {
                Element newsElement=element.selectFirst("div.story-text");
                String topic = newsElement.selectFirst("h3 a").text();
                String imageUrl=newsElement.selectFirst("div.thumb-image a img").attr("src");
                String description=newsElement.selectFirst("p").text();
                String link=newsElement.selectFirst("a").attr("href");
                adaDeranaNews.add(new News(topic,description,url+link,imageUrl));
            }
            newsMap.put("adaDerana",adaDeranaNews);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<News> newsFistNews = new ArrayList<>();
            String url = "https://sinhala.newsfirst.lk/latest-news/";
            Document document = Jsoup.connect(url).get();

            Elements elements=document.select("div.lap_news_div div div div div.local_news_main div.ng-star-inserted");

            for (Element element : elements) {
                Element newsElement=element.selectFirst("a");
                String topic = newsElement.selectFirst("div div h2.local_news_sub_header").text();

                String imageUrl=newsElement.selectFirst("div img").attr("src");
                String description=newsElement.selectFirst("div div div.top_stories_sub_detail").text().split("-")[1];
                String link=newsElement.attr("href");
                newsFistNews.add(new News(topic,description,"https://sinhala.newsfirst.lk"+link,imageUrl));
            }
            newsMap.put("newsFirst",newsFistNews);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<News> hiruNews = new ArrayList<>();
            String url = "https://www.hirunews.lk/";
            Document document = Jsoup.connect(url).get();

            Elements elements=document.select("div.main-article-section div.row");

            for (Element element : elements) {
                Element aTag= element.selectFirst("div.column.left");
                Element aTag2=element.selectFirst("div.column.middle div.section-tittle a");
                if(aTag==null || aTag2==null) continue;
                String topic= aTag2.text();
                String imageUrl=aTag.select("img").attr("data-src");
                String description="";
                String link=aTag.selectFirst("a").attr("href");
                hiruNews.add(new News(topic,description,link,imageUrl));
            }
            newsMap.put("hiru",hiruNews);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newsMap;
    }
}
