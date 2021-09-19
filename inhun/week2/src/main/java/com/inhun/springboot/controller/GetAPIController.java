package com.inhun.springboot.controller;

import com.inhun.springboot.model.Articles;
import com.inhun.springboot.model.Attachments;
import com.inhun.springboot.service.AttachmentsService;
import com.inhun.springboot.service.ArticlesService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GetAPIController {

    private final ArticlesService articlesService;
    private final AttachmentsService attachmentsService;

    public GetAPIController(ArticlesService articlesService, AttachmentsService attachmentsService) {
        this.articlesService = articlesService;
        this.attachmentsService = attachmentsService;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String getRequest() {
        return "this is InHun Choi";
    }

    // 게시글 전체 조회
    @RequestMapping(method = RequestMethod.GET, path = "/articles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Articles>> getAllArticles() {
        List<Articles> articles = articlesService.findAll();
        return new ResponseEntity<List<Articles>>(articles, HttpStatus.OK);
    }
    // 게시글 업로드
    @RequestMapping(method = RequestMethod.POST, path = "/articles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Articles> postArticle(@RequestBody(required = false) Articles body) {
        articlesService.save(body);
        if (body.getUploads_id() != null) {
            Attachments attachments = new Attachments(body.getId(), body.getUploads_id());
            attachmentsService.save(attachments);
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
    public ResponseEntity<Articles> updateArticle(@PathVariable("id") Long id, @RequestBody Articles body) {
        articlesService.updateById(id, body);
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // 게시글 삭제
    @RequestMapping(method = RequestMethod.DELETE, path = "/articles/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") Long id) {
        System.out.println(id);
        articlesService.deleteById(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}