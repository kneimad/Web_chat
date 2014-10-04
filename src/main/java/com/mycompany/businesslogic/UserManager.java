/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.businesslogic;

import com.mycompany.beans.User;
import org.apache.log4j.Logger;

import java.util.*;

public class UserManager {
    private Map<User,Date> users;
    private static UserManager instance = new UserManager();

    private static final Logger log = Logger.getLogger(UserManager.class);

    private UserManager() {
        users = new HashMap<User, Date>();
    }

    public static UserManager getInstance() {
        return instance;
    }

    public Date getLoginTime(User user)
    {
        return users.get(user);
    }

    public void addUser(User user)
    {
        users.put(user, new Date());

        log.info("The new user was added: " + user.toString());
    }

    public boolean isUserNameUsed(User user)
    {
        return users.containsKey(user);
    }

    public Map<User, Date> getUsers() {
        return users;
    }

    public void removeUser(User user)
    {
        users.remove(user);
        log.info("The user was removed: " + user.toString());
    }
}
