package com.example.demo.models;

public class ReponseObject {
    private boolean success;
    private String message;

    public ReponseObject() {
    }

    public ReponseObject(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
