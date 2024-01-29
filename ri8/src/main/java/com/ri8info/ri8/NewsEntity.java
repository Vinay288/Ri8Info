package com.ri8info.ri8;

import lombok.Data;

@Data
public class NewsEntity {
    private String author;
    private String content;
    private String createdAt;
    private String description;
    private String title;
    private String url;
    private String urlToImage;
    private String password;
}
