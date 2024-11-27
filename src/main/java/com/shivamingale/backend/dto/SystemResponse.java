package com.shivamingale.backend.dto;

public class SystemResponse<T> {
    private boolean status;
    private String message;
    private T data;  // Generic field to hold any type of response object

    public SystemResponse() {
        this.status = false;
        this.message = "";
    }

    // Constructor to set status and message
    public SystemResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    // Constructor to set status, message, and response object
    public SystemResponse(boolean status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}