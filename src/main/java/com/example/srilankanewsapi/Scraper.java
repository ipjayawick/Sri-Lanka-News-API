package com.example.srilankanewsapi;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {
    public static List<News> scrapeNews() {
        List<News> news = new ArrayList<>();
        try {
            // Fetch the HTML content
            String url = "https://www.lankadeepa.lk/latest_news/1";
            Document document = Jsoup.connect(url).get();

            // Select the <section> tag with class 'bg0 p-t-10 p-b-0'
            Element latestSection = document.selectFirst("section.bg0.p-t-10.p-b-0");

            // Check if the section is found
            if (latestSection != null) {
                // Select the div with class 'flex-wr-sb-s p-t-20 p-b-15 how-bor2 row'
                Elements postDivs = latestSection.select("div.flex-wr-sb-s.p-t-20.p-b-15.how-bor2.row");

                for (Element postDiv : postDivs) {
                    // Extract the first <a> tag's href attribute
                    String source = postDiv.selectFirst("a").attr("href");

                    // Extract the topic and description
                    Element col7Div = postDiv.selectFirst(".col-7");
                    if (col7Div != null) {
                        Elements h5Tags = col7Div.select("h5");
                        String topic = h5Tags.first() != null ? h5Tags.first().text().trim() : "";
                        String description = h5Tags.last() != null ? h5Tags.last().text().trim() : "";

                        // Store the extracted data in the list
                        news.add(new News(topic, description,source));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error fetching page: " + e.getMessage());
        }

        return news;
    }
}
