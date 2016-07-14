package com.kdavidenko.springmvc.service;

import java.util.List;

import com.kdavidenko.springmvc.model.Article;

public interface ArticleService {

    Article findById(Integer id);

    void saveArticle(Article article);

    void updateArticle(Article article);

    void deleteArticle(Integer id);

    List<Article> findAllArticles();
}
