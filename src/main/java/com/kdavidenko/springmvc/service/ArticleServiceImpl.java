package com.kdavidenko.springmvc.service;

import java.util.List;

import com.kdavidenko.springmvc.model.Article;
import com.kdavidenko.springmvc.dao.ArticleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao dao;

    public Article findById(Integer id) {
        return dao.findById(id);
    }

    public void saveArticle(Article article) {
        dao.saveArticle(article);
    }

    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends.
     */
    public void updateArticle(Article article) {
        Article entity = dao.findById(article.getId());
        if (entity != null) {
            entity.setTitle(article.getTitle());
            entity.setPublicationDate(article.getPublicationDate());
            entity.setContent(article.getContent());
            entity.setCategory(article.getCategory());
        }
    }

    public void deleteArticle(Integer id) {
        dao.deleteArticle(id);
    }

    public List<Article> findAllArticles() {
        return dao.findAllArticles();
    }
}
