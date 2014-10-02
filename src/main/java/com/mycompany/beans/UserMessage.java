/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.beans;

import java.util.Date;

/**
 *
 * @author Damien
 */
public class UserMessage {
    private  Date time;
    private final User user;
    private final String message;

    public UserMessage(User user, String message) {
        this.user = user;
        this.message = message;
    }

    public Date getTime() {
        return time;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
}
