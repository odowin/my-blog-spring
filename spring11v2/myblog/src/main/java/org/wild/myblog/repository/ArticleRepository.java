package org.wild.myblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.wild.myblog.model.Article;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByTitle(String title);

    // Recherche d'articles contenant un mot-clé dans le contenu
    List<Article> findByContentContaining(String keyword);

    // Recherche des articles créés après une date donnée
    List<Article> findByCreatedAtAfter(LocalDateTime date);

    // Récupération des 5 derniers articles créés (du plus récent au plus ancien)
    @Query("SELECT a FROM Article a ORDER BY a.createdAt DESC")
    List<Article> findTop5ByOrderByCreatedAtDesc();

}