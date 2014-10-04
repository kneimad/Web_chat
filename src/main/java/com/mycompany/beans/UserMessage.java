/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;

import java.util.Date;

/**
 * @author Damien
 */
public class UserMessage {
    private final User user;
    private final String message;
    private Date time;

    public UserMessage(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "UserMessage{" +
                "time=" + time +
                ", user=" + user +
                ", message='" + message + '\'' +
                '}';
    }
}
