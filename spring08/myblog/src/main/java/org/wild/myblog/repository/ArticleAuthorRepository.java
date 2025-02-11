package org.wild.myblog.repository;

import org.wild.myblog.model.ArticleAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleAuthorRepository extends JpaRepository<ArticleAuthor, Long> {
}