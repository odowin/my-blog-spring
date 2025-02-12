package org.wild.myblog.mapper;

import org.springframework.stereotype.Component;
import org.wild.myblog.dto.ArticleDTO;
import org.wild.myblog.dto.CategoryDTO;
import org.wild.myblog.model.Category;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        if (category.getArticles() != null) {
            categoryDTO.setArticles(category.getArticles().stream().map(article -> {
                ArticleDTO articleDTO = new ArticleDTO();
                articleDTO.setId(article.getId());
                articleDTO.setTitle(article.getTitle());
                articleDTO.setContent(article.getContent());
                articleDTO.setUpdatedAt(article.getUpdatedAt());
                articleDTO.setCategoryName(article.getCategory().getName());
                return articleDTO;
            }).collect(Collectors.toList()));
        }
        return categoryDTO;
    }
}