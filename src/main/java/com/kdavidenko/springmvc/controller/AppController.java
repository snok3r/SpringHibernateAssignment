package com.kdavidenko.springmvc.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.kdavidenko.springmvc.model.Article;
import com.kdavidenko.springmvc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class AppController {

    @Qualifier("articleService")
    @Autowired
    ArticleService service;

    @Autowired
    MessageSource messageSource;

    private final String mainPage = "main";
    private final String addArticlePage = "addarticle";

    /*
     * This method will list all existing articles.
     */
    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String getAllArticles(ModelMap model) {

        List<Article> articles = service.findAllArticles();
        model.addAttribute("articles", articles);
        return mainPage;
    }

    /*
    This method will list articles those matching <tt>category</tt>.
     */
    @RequestMapping(value = {"/search/category={category}"}, method = RequestMethod.GET)
    public String getArticlesByCategory(@PathVariable String category,
                                        ModelMap model) {

        String decodedCategory = decodeISOString(category);
        List<Article> articles = service.findArticlesByCategory(decodedCategory);

        model.addAttribute("articles", articles);
        model.addAttribute("message", articles.size() + " результат(ов/а) в категории '" + decodedCategory + "'");
        model.addAttribute("alert_clazz", "info");
        return mainPage;
    }

    /*
    This method will list articles those matching <tt>searchPattern</tt> in
    title or content.
    */
    @RequestMapping(value = {"/search/titleandcontent={searchPattern}"}, method = RequestMethod.GET)
    public String getArticlesByTitleOrContent(@PathVariable String searchPattern,
                                              ModelMap model) {

        String decodedSearchPattern = decodeISOString(searchPattern);
        List<Article> articles = service.findArticlesByTitleOrContent(decodedSearchPattern);

        model.addAttribute("articles", articles);
        model.addAttribute("message", articles.size() + " результат(ов/а) по ключевому слову '" + decodedSearchPattern + "'");
        model.addAttribute("alert_clazz", "info");
        return mainPage;
    }

    private String decodeISOString(String stringToDecode) {
        try {
            return new String(stringToDecode.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /*
     * This method will provide the medium to add a new article.
     */
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String newArticle(ModelMap model) {
        Article article = new Article();
        model.addAttribute("article", article);
        model.addAttribute("edit", false);
        return addArticlePage;
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving article in database. It also validates the user input
     */
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String saveArticle(@Valid Article article, BindingResult result,
                              ModelMap model) {

        if (result.hasErrors()) {
            return addArticlePage;
        }

        service.saveArticle(article);

        List<Article> articles = service.findAllArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("message", "Новость '" + article.getTitle() + "' успешно добавлена");
        model.addAttribute("alert_clazz", "success");
        return mainPage;
    }


    /*
     * This method will provide the medium to update an existing article.
     */
    @RequestMapping(value = {"/edit-{id}-article"}, method = RequestMethod.GET)
    public String editArticle(@PathVariable Integer id, ModelMap model) {
        Article article = service.findById(id);
        model.addAttribute("article", article);
        model.addAttribute("edit", true);
        return addArticlePage;
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating article in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-{id}-article"}, method = RequestMethod.POST)
    public String updateArticle(@Valid Article article, BindingResult result,
                                ModelMap model, @PathVariable Integer id) {

        if (result.hasErrors()) {
            return addArticlePage;
        }

        service.updateArticle(article);

        List<Article> articles = service.findAllArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("message", "Новость '" + article.getTitle() + "' успешно обновлена");
        model.addAttribute("alert_clazz", "success");
        return mainPage;
    }


    /*
     * This method will delete an article by it's id value.
     */
    @RequestMapping(value = {"/delete-{id}-article"}, method = RequestMethod.GET)
    public String deleteArticle(@PathVariable Integer id, ModelMap model) {
        service.deleteArticle(id);

        List<Article> articles = service.findAllArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("message", "Новость с id = '" + id + "' успешно удалена");
        model.addAttribute("alert_clazz", "warning");
        return mainPage;
    }

}
