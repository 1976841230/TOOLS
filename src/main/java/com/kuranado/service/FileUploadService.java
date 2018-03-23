package com.kuranado.service;

import com.google.gson.Gson;
import com.kuranado.ocr.Ocr;
import com.kuranado.wrapper.UploadResultEnum;
import com.kuranado.wrapper.UploadResultWrapper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.IOException;

/**
 * Created by JING on 2018/3/14.
 */
@Service
public class FileUploadService {

    @Resource(name = "ocr")
    private Ocr ocr;
    @Resource(name = "uploadResultWrapper")
    private UploadResultWrapper uploadResultWrapper;

    /**
     * 处理上传的文件
     * @param file 前端上传的文件，要求必须是图片文件
     * @param request
     * @return 返回提示信息，提示用户上传结果
     */
    public String uploadImage(MultipartFile file, HttpServletRequest request) {

        Gson gson = new Gson();

        if (file.isEmpty()) {
            uploadResultWrapper.setCode(UploadResultEnum.EMPTY.getCode());
            uploadResultWrapper.setDescription(UploadResultEnum.EMPTY.getDescription());
            return gson.toJson(uploadResultWrapper);
        }

        if (! isImage(file)) {
            uploadResultWrapper.setCode(UploadResultEnum.TYPE.getCode());
            uploadResultWrapper.setDescription(UploadResultEnum.TYPE.getDescription());
            return gson.toJson(uploadResultWrapper);
        }

        String path = request.getSession().getServletContext().getRealPath("/img-upload") + "/" + file.getOriginalFilename();
        File targetFile = new File(path);
        try {
            file.transferTo(targetFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String recognitionContent = ocr.ocr(path);

        if (recognitionContent != null && ! "".equals(recognitionContent)) {
            deleteFile(targetFile);
        }

        //uploadResultWrapper.setCode(UploadResultEnum.SUCCESS.getCode());
        //uploadResultWrapper.setDescription(recognitionContent);
        //recognitionContent = gson.toJson(recognitionContent);
        //recognitionContent = recognitionContent.replaceAll("\n*\\\\*", "");

        return recognitionContent;

    }

    /**
     * 判断文件是否是图片
     * @param file
     * @return
     */
    private Boolean isImage(MultipartFile file) {
        return file.getContentType().startsWith("image");
    }

    /**
     * 删除文件
     * @param file
     */
    private void deleteFile(File file) {
        file.delete();
    }

}