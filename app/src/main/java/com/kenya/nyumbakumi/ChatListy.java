package com.kenya.nyumbakumi;

/**
 * Created by Nderitu on 15/11/2017.
 */

public class ChatListy {
    private String time;
    private String names;
    private String message;

    public ChatListy(String time, String names, String message) {
        this.time = time;
        this.names = names;
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
