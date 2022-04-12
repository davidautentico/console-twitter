package com.drosa.twitter.domain;

import java.util.Objects;

/**
 * Representa a un usuario de chat que cuenta con un timeline y una lista de usuarios
 * a los que sigue
 */
public class User {
    private final String name;
    private final Timeline timeLine;
    private final FollowedList followedList;

    public User(String name) {
        this.name = name;
        this.timeLine = new Timeline();
        this.followedList = new FollowedList();
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

    public Timeline getTimeLine() {
        return timeLine;
    }

    public FollowedList getFollowedList() {
        return followedList;
    }
}
