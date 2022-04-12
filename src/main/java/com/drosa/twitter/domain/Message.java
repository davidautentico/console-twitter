package com.drosa.twitter.domain;

import java.time.Instant;

/**
 * Representa un objeto mensaje inmutable, realizado por un usuario en un instante
 * concreto de tiempo
 */
public class Message {

    private final User user;
    private final String message;
    private final Instant createdAt;

    public Message(User user, String message, Instant createdAt) {
        this.user = user;
        this.message = message;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
