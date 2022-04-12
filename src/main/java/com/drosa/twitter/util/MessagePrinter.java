package com.drosa.twitter.util;

import com.drosa.twitter.domain.Message;

public class MessagePrinter {

    private static final String MESSAGE_FORMAT_WALL = "%s - %s %s";
    private static final String MESSAGE_FORMAT_TIMELINE = "%s %s";

    public static void printMessageForWall(Message message) {
        System.out.println(String.format(MESSAGE_FORMAT_WALL, message.getUser().getName(), message.getMessage(),
                TimeFormatter.getTimeDifference(message.getCreatedAt())));
    }

    public static void printMessageForTimeLine(Message message) {
        System.out.println(String.format(MESSAGE_FORMAT_TIMELINE, message.getMessage(),
                TimeFormatter.getTimeDifference(message.getCreatedAt())));
    }
}
