/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.businesslogic;

import com.mycompany.beans.User;

import java.util.*;

public class UserManager {
    private Set<User> users;
    private static UserManager instance = new UserManager();

    private UserManager() {
        users = new HashSet<User>();
    }

    public static UserManager getInstance() {
        return instance;
    }

    public void addUser(User user)
    {
        users.add(user);
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
    }
}
