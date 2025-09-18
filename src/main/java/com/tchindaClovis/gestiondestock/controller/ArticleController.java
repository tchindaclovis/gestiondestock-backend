package com.tchindaClovis.gestiondestock.controller;

import com.tchindaClovis.gestiondestock.controller.api.ArticleApi;
import com.tchindaClovis.gestiondestock.dto.ArticleDto;
import com.tchindaClovis.gestiondestock.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static com.tchindaClovis.gestiondestock.utils.Constants.APP_ROOT;

@RestController
public class ArticleController implements ArticleApi {

    private ArticleService articleService;

    @Autowired  //Constructor injection sur le constructeur
    public ArticleController(ArticleService articleService){

        this.articleService = articleService;
    }

    @Override
    public ArticleDto save(ArticleDto dto) {

        return articleService.save(dto);
    }

    @Override
    public ArticleDto findById(Integer id) {

        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {

        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public List<ArticleDto> findAll() {

        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {

        articleService.delete(id);
    }
}
