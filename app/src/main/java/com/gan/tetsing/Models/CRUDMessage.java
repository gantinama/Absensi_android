package com.gan.tetsing.Models;

import com.google.gson.annotations.SerializedName;

public class CRUDMessage {
    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private String message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
