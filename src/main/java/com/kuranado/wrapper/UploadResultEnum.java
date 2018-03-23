package com.kuranado.wrapper;

/**
 * Created by JING on 2018/3/14.
 */
public enum UploadResultEnum {


    EMPTY(0, "文件不能为空"),
    TYPE(1, "请上传图片格式文件"),
    SUCCESS(3, "上传成功");

    private int code;
    private String description;

    UploadResultEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
