package com.example.chatbot_application;

public class chatModel {
    private String message;
    private String sender;

    public chatModel(String message, String sender) {
        this.message = message;
        this.sender = sender;
    }

    public chatModel() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}