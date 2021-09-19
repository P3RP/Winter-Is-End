package com.inhun.springboot.controller;

import com.inhun.springboot.model.Articles;
import com.inhun.springboot.service.ArticlesService;

import org.hibernate.annotations.common.reflection.XMethod;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class GetAPIController {

    private final ArticlesService articlesService;

    public GetAPIController(ArticlesService articlesService) {
        this.articlesService = articlesService;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String getRequest() {
        return "this is InHun Choi";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/articles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Articles>> getAllArticles() {
        List<Articles> articles = articlesService.findAll();
        return new ResponseEntity<List<Articles>>(articles, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/articles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Articles> postArticle(@RequestBody Articles body) {
        articlesService.save(body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/articles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Articles> updateArticle(@PathVariable("id") Long id, @RequestBody Articles body) {
        articlesService.updateById(id, body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/articles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        System.out.println(id);
        articlesService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}