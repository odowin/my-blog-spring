package org.wild.myblog.mapper;

import org.springframework.stereotype.Component;
import org.wild.myblog.dto.ImageDTO;
import org.wild.myblog.model.Article;
import org.wild.myblog.model.Image;

import java.util.stream.Collectors;

@Component
public class ImageMapper {

    public ImageDTO convertToDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setUrl(image.getUrl());
        if (image.getArticles() != null) {
            imageDTO.setArticleIds(image.getArticles().stream().map(Article::getId).collect(Collectors.toList()));
        }
        return imageDTO;
    }
}