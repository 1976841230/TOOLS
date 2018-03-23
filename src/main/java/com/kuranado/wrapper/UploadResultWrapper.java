package com.kuranado.wrapper;

import org.springframework.stereotype.Component;

/**
 * Created by JING on 2018/3/16.
 */
@Component
public class UploadResultWrapper {

    private int code;
    private String description;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
