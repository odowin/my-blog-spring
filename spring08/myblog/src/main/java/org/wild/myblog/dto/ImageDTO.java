package org.wild.myblog.dto;

import java.util.List;

public class ImageDTO {
    private Long id;
    private String url;
    private List<Long> articleIds;

    // Getters et setters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getArticleIds() {
        return articleIds;
    }

    public void setArticleIds(List<Long> articleIds) {
        this.articleIds = articleIds;
    }
}

