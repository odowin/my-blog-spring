package org.wild.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wild.myblog.model.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}