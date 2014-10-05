package com.mycompany.businesslogic;

import com.mycompany.beans.User;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Manages users of the chat.
 * <p/>
 * Lets to add and remove user, gets all added users, gets user's login time
 * and checks is user logged in.
 *
 * @author Damien
 */
public class UserManager {
    /**
     * Logger for current class by log4j.
     */
    private static final Logger log = Logger.getLogger(UserManager.class);
    /**
     * Solo instance of {@code UserManager} class.
     */
    private static UserManager instance = new UserManager();
    /**
     * Set of entries user and its login time.
     */
    private final Map<User, Date> users;

    /**
     * Solo constructor.
     */
    private UserManager() {
        users = new HashMap<User, Date>();
    }

    /**
     * Provides the only instance of {@code UserManager} class.
     * @return instance of {@code UserManager} class.
     */
    public static UserManager getInstance() {
        return instance;
    }

    /**
     * Gets login time of given user.
     * @param user is a user of a chat.
     * @return login time of given user.
     */
    public Date getLoginTime(User user) {
        return users.get(user);
    }

    /**
     * Adds given user to the set of logged users, if this user not in the set.
     * @param user is a user of a chat.
     */
    public boolean addUser(User user) {
        synchronized (users) {
            if (!users.containsKey(user)) {
                users.put(user, new Date());
                return true;
            }
            return false;
        }
    }

    /**
     * Gets all logged users.
     * @return set of users and their login time.
     */
    public Set<User> getUsers() {
        return new HashSet<User>(users.keySet());
    }

    /**
     * Removes a user from the set of logged users.
     * @param user is a user of a chat.
     */
    public void removeUser(User user) {
        synchronized (users) {
            users.remove(user);
        }
        log.info("The user was removed: " + user.toString());
    }
}
