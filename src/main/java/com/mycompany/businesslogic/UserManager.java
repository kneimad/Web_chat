/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.businesslogic;

import com.mycompany.beans.User;
import org.apache.log4j.Logger;

import java.util.*;

public class UserManager {
    private Set<User> users;
    private static UserManager instance = new UserManager();

    private static final Logger log = Logger.getLogger(UserManager.class);

    private UserManager() {
        users = new HashSet<User>();
    }

    public static UserManager getInstance() {
        return instance;
    }

    public void addUser(User user)
    {
        users.add(user);
        log.info("The new user was added: " + user.toString());
    }

    public boolean isUserNameUsed(User user)
    {
        return users.contains(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void removeUser(User user)
    {
        users.remove(user);
        log.info("The user was removed: " + user.toString());
    }
}
