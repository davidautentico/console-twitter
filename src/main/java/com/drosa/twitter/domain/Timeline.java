package com.drosa.twitter.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa una secuencia de mensajes
 */
public class Timeline {

    private final List<Message> messageList;

    public Timeline() {
        this.messageList = new ArrayList<>();
    }

    public void addMessage(Message message) {
        messageList.add(message);
    }

    public List<Message> getMessages() {
        return messageList;
    }
}
