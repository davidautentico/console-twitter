package com.drosa.twitter.infraestructure.printer;

import com.drosa.twitter.domain.entity.Message;
import com.drosa.twitter.domain.service.printer.MessagePrinter;

public class MessagePrinterImpl implements MessagePrinter {

    private static final String MESSAGE_FORMAT_WALL = "%s - %s %s";
    private static final String MESSAGE_FORMAT_TIMELINE = "%s %s";

    private final TimeFormatter timeFormatter;

    public MessagePrinterImpl(TimeFormatter timeFormatter) {
        this.timeFormatter = timeFormatter;
    }

    public void printMessageForWall(Message message) {
        System.out.println(String.format(MESSAGE_FORMAT_WALL, message.getUser().getName(), message.getMessage(),
            timeFormatter.getTimeDifference(message.getCreatedAt())));
    }

    public void printMessageForTimeLine(Message message) {
        System.out.println(String.format(MESSAGE_FORMAT_TIMELINE, message.getMessage(),
            timeFormatter.getTimeDifference(message.getCreatedAt())));
    }
}
