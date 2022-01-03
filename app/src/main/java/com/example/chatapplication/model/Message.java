package com.example.chatapplication.model;

public class Message {

    String senderName;
    String receiverName;
    String message;
    String timeStamp;

    public Message(String senderName, String receiverName, String message, String timeStamp) {
        this.senderName = senderName;
        this.receiverName = receiverName;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    Message(){

    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
