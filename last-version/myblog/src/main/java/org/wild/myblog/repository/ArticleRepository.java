package org.wild.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wild.myblog.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // Retourne une liste d'articles contenant une chaîne spécifique dans leur contenu
    List<Article> findByContentContaining(String content);

    // Retourne une liste d'articles créés après une date donnée
    List<Article> findByCreatedAtAfter(LocalDateTime date);

    // Retourne les 5 derniers articles créés, classés du plus récent au plus ancien
    @Query("SELECT a FROM Article a ORDER BY a.createdAt DESC")
    List<Article> findTop5ByOrderByCreatedAtDesc();
}
