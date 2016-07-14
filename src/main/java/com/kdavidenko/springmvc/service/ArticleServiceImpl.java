package com.kdavidenko.springmvc.service;

import java.util.List;

import com.kdavidenko.springmvc.model.Article;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public Article findById(Integer id) {
        return (Article) getSession().get(Article.class, id);
    }

    public void saveArticle(Article article) {
        getSession().save(article);
    }

    public void updateArticle(Article article) {
        Article entity = findById(article.getId());
        if (entity != null) {
            entity.setTitle(article.getTitle());
            entity.setContent(article.getContent());
            entity.setCategory(article.getCategory());
        }
    }

    public void deleteArticle(Integer id) {
        Query query = getSession().createSQLQuery("DELETE FROM articles WHERE id = :id");
        query.setInteger("id", id);
        query.executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public List<Article> findAllArticles() {
        Criteria criteria = createEntityCriteria();
        return (List<Article>) criteria.list();
    }

    private Criteria createEntityCriteria() {
        return getSession().createCriteria(Article.class);
    }
}
