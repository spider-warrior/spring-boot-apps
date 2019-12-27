package com.study.test.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("file")
@RestController
public class FileController {

    @PostMapping
    public String createFile(@RequestParam("username") String username, @RequestParam("file") MultipartFile multipartFile) {
        System.out.println("username: " + username);
        System.out.println("file: " + multipartFile.getOriginalFilename());
        return "success";
    }

}
