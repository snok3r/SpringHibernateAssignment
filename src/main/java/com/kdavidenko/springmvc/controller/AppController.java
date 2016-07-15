package com.kdavidenko.springmvc.controller;

import java.util.List;

import com.kdavidenko.springmvc.model.Article;
import com.kdavidenko.springmvc.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ArticleService service;

    @Autowired
    MessageSource messageSource;

    /*
     * This method will list all existing articles.
     */
    @RequestMapping(value = {"/", "/main"}, method = RequestMethod.GET)
    public String getAllArticles(ModelMap model) {

        List<Article> articles = service.findAllArticles();
        model.addAttribute("articles", articles);
        return "main";
    }

    /*
     * This method will provide the medium to add a new article.
     */
    @RequestMapping(value = {"/add"}, method = RequestMethod.GET)
    public String newArticle(ModelMap model) {
        Article article = new Article();
        model.addAttribute("article", article);
        model.addAttribute("edit", false);
        return "addarticle";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving article in database. It also validates the user input
     */
    @RequestMapping(value = {"/add"}, method = RequestMethod.POST)
    public String saveArticle(@Valid Article article, BindingResult result,
                              ModelMap model) {

        if (result.hasErrors()) {
            return "addarticle";
        }

        service.saveArticle(article);

        List<Article> articles = service.findAllArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("message", "Новость '" + article.getTitle() + "' успешно добавлена");
        return "main";
    }


    /*
     * This method will provide the medium to update an existing article.
     */
    @RequestMapping(value = {"/edit-{id}-article"}, method = RequestMethod.GET)
    public String editArticle(@PathVariable Integer id, ModelMap model) {
        Article article = service.findById(id);
        model.addAttribute("article", article);
        model.addAttribute("edit", true);
        return "addarticle";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * updating article in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-{id}-article"}, method = RequestMethod.POST)
    public String updateArticle(@Valid Article article, BindingResult result,
                                ModelMap model, @PathVariable Integer id) {

        if (result.hasErrors()) {
            return "addarticle";
        }

        service.updateArticle(article);

        List<Article> articles = service.findAllArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("message", "Новость '" + article.getTitle() + "' успешно обновлена");
        return "main";
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
        model.addAttribute("delete", true);
        return "main";
    }

}
