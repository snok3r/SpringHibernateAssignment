package com.kdavidenko.springmvc.dao;

import java.util.List;

import com.kdavidenko.springmvc.model.Article;

public interface ArticleDao {

    Article findById(Integer id);

    void saveArticle(Article article);

    void deleteArticle(Integer id);

    List<Article> findAllArticles();
}
