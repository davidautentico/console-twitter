package com.drosa.twitter.domain.usecase;

import com.drosa.twitter.domain.entity.Message;
import com.drosa.twitter.domain.entity.User;
import com.drosa.twitter.domain.repository.UserRepository;
import com.drosa.twitter.domain.service.printer.MessagePrinter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimelineCommandUseCase implements CommandUseCase {

    private static final Pattern REGEX = Pattern.compile("^\\S+$");

    private final UserRepository userRepository;

    private final MessagePrinter messagePrinter;

    public TimelineCommandUseCase(UserRepository userRepository, MessagePrinter messagePrinter) {
        this.userRepository = userRepository;
        this.messagePrinter = messagePrinter;
    }

    public boolean execute(String commandLine) {
        String userName = commandLine.trim();

        User user = userRepository.getUserOrAdd(userName);

        //no se ordena, se obtiene en reversed order y se evita el tiempo de ordenación
        List<Message> messageList = user.getTimeLine();
        int size = messageList.size();
        for (int i = size - 1; i >= 0; i--)
            messagePrinter.printMessageForTimeLine(messageList.get(i));

        return true;
    }

    public boolean matches(String input) {
        if (input != null && !input.isEmpty()) {
            Matcher matcher = REGEX.matcher(input);
            return matcher.find();
        }
        return false;
    }
}
