/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.hello;

import java.util.*;

/**
 *
 * @author Damien
 */
public class UserManager {
    private Set<User> users = new HashSet<User>();
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
