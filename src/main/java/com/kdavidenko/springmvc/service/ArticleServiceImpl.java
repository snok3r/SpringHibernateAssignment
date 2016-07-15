package com.kdavidenko.springmvc.service;

import java.util.List;
import java.util.stream.Collectors;

import com.kdavidenko.springmvc.model.Article;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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

    @Override
    public Article findById(Integer id) {
        return (Article) getSession().get(Article.class, id);
    }

    @Override
    public void saveArticle(Article article) {
        getSession().save(article);
    }

    @Override
    public void updateArticle(Article article) {
        Article entity = findById(article.getId());
        if (entity != null) {
            entity.setTitle(article.getTitle());
            entity.setContent(article.getContent());
            entity.setCategory(article.getCategory());
        }
    }

    @Override
    public void deleteArticle(Integer id) {
        Article articleToDelete = findById(id);
        getSession().delete(articleToDelete);
    }

    @Override
    public List<Article> findArticlesByCategory(final String category) {
        if (category == null || "".equals(category))
            return findAllArticles();

        return findAllArticles()
                .parallelStream()
                .filter(a -> a.getCategory().toLowerCase().equals(category.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Article> findArticlesByTitleOrContent(final String searchPattern) {
        if (searchPattern == null || "".equals(searchPattern))
            return findAllArticles();

        return findAllArticles()
                .parallelStream()
                .filter(article -> titleOrContentContainsPattern(article, searchPattern))
                .collect(Collectors.toList());
    }

    private boolean titleOrContentContainsPattern(Article article, String searchPattern) {
        return titleContainsPattern(article, searchPattern) || contentContainsPattern(article, searchPattern);
    }

    private boolean titleContainsPattern(Article article, String pattern) {
        return article.getTitle().toLowerCase().contains(pattern.toLowerCase());
    }

    private boolean contentContainsPattern(Article article, String pattern) {
        return article.getContent().toLowerCase().contains(pattern.toLowerCase());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Article> findAllArticles() {
        Criteria criteria = createEntityCriteria();
        return (List<Article>) criteria
                .addOrder(Order.desc("id"))
                .list();
    }

    private Criteria createEntityCriteria() {
        return getSession().createCriteria(Article.class);
    }
}
