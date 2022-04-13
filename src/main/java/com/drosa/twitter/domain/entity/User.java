package com.drosa.twitter.domain.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * Representa a un usuario de chat que cuenta con un timeline y una lista de usuarios
 * a los que sigue
 */
public class User {
    private final String name;
    private final List<Message> timeLine;
    private final HashSet<User> followedList;

    public List<Message> getTimeLine() {
        return timeLine;
    }

    public HashSet<User> getFollowedList() {
        return followedList;
    }

    public User(String name) {
        this.name = name;
        this.timeLine = new ArrayList<>();
        this.followedList = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    public void addMessage(Message message){
        timeLine.add(message);
    }

    public boolean addFollowed(User user){
        return followedList.add(user);
    }
}
