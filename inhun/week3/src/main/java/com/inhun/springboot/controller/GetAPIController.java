package com.inhun.springboot.controller;

import com.inhun.springboot.model.Article;
import com.inhun.springboot.model.Attachment;
import com.inhun.springboot.service.AttachmentService;
import com.inhun.springboot.service.ArticleService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"Article API"})
@RequestMapping("/api")
public class GetAPIController {

    private final ArticleService articleService;
    private final AttachmentService attachmentService;


    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String getRequest() {
        return "this is InHun Choi";
    }

    // 게시글 전체 조회
    @RequestMapping(method = RequestMethod.GET, path = "/articles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleService.findAll();
        return new ResponseEntity<List<Article>>(articles, HttpStatus.OK);
    }

    // 게시물 조회
    @RequestMapping(method = RequestMethod.GET, path="/articles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Article> getArticle(@PathVariable("id") Long id) {
        Article article = articleService.findById(id);
        return new ResponseEntity<>(article, HttpStatus.OK);
    }


    // 게시글 업로드
    @RequestMapping(method = RequestMethod.POST, path = "/articles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Article> postArticle(@RequestBody(required = false) Article body, Principal principal) {
        body.setCreatedBy(principal.getName());
        articleService.save(body);
        System.out.println(principal.getName());
        if (body.getUploads_id() != null) {
            Attachment attachment = new Attachment(body.getId(), body.getUploads_id());
            attachmentService.save(attachment);
        }
        return new ResponseEntity<>(body, HttpStatus.OK);
    }
    
    // 게시글, 파일 업로드

    /*

    @RequestMapping(method = RequestMethod.POST, path = "/articles", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> postArticle(
            @RequestPart String article,
            @RequestPart("file") MultipartFile file
    ) {
        if (file.isEmpty()==false) {
            Attachment attachment = articlesService.getJson(article, file);
            System.out.println(attachment);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            System.out.println("gggggggg");
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }

    */

    // 게시글 수정
    @RequestMapping(method = RequestMethod.PUT, path = "/articles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Article> updateArticle(@PathVariable("id") Long id, @RequestBody Article body) {
        articleService.updateById(id, body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // 게시글 삭제
    @RequestMapping(method = RequestMethod.DELETE, path = "/articles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        System.out.println(id);
        articleService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}