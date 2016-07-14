package com.kdavidenko.springmvc.dao;

import java.util.List;

import com.kdavidenko.springmvc.model.Article;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public void persist(Article entity) {
        getSession().persist(entity);
    }

    public void delete(Article entity) {
        getSession().delete(entity);
    }

    public Article findById(Integer id) {
        return (Article) getSession().get(Article.class, id);
    }

    public void saveArticle(Article article) {
        persist(article);
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
