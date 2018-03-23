package com.kuranado.controller;

import com.kuranado.service.FileUploadService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUploadController {

    @Resource(name = "fileUploadService")
    private FileUploadService fileUploadService;

    @GetMapping("upload")
    public String fileUpload() {
        return "file-upload";
    }

    @PostMapping("/upload/image")
    public @ResponseBody
    String uploadImage(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return fileUploadService.uploadImage(file, request);
    }

}
