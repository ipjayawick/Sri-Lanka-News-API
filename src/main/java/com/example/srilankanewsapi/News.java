package com.example.srilankanewsapi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class News {

    private String topic;
    private String description;
    private String link;
    private String imageUrl;


}