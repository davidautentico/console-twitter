package com.drosa.twitter.domain;

import java.util.HashSet;

/**
 * Represanta un conjunto único de usuarios
 */
public class FollowedList {

    private final HashSet<User> userList;

    public FollowedList() {
        userList = new HashSet<>();
    }

    public boolean addUserToList(User user) {
        return userList.add(user);
    }

    public HashSet<User> getUserList() {
        return userList;
    }

}
