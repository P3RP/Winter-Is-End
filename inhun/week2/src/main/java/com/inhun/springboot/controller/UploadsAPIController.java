package com.inhun.springboot.controller;


import com.inhun.springboot.model.Uploads;
import com.inhun.springboot.service.UploadsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/files")

public class UploadsAPIController {

    private UploadsService uploadsService;

    public UploadsAPIController(UploadsService uploadsService) {
        this.uploadsService = uploadsService;
    }

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
        Uploads upload = new Uploads(files.getOriginalFilename(), filePath);
        uploadsService.save(upload);
        return "good";

    }
}
