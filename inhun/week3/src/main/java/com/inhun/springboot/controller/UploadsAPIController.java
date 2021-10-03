package com.inhun.springboot.controller;


import com.inhun.springboot.model.Upload;
import com.inhun.springboot.service.UploadService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequiredArgsConstructor
@Api(tags = {"Upload API"})
@RequestMapping("/files")
public class UploadsAPIController {

    private final UploadService uploadService;

    @RequestMapping(method = RequestMethod.GET, path = "/test")
    public String getRequest() {
        return "this is InHun Choi";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/uploads")
    public String uploadFile(@RequestParam("file") MultipartFile files) throws Exception {
        String baseDir = "C:\\Users\\choi\\SpringBoot\\uploads";
        String filePath = baseDir + "\\" + files.getOriginalFilename();
        System.out.println(filePath);
        files.transferTo(new File(filePath));
        Upload upload = new Upload(files.getOriginalFilename(), filePath);
        uploadService.save(upload);
        return "good";

    }
}
