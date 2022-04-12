package com.drosa.twitter.commandService;

import com.drosa.twitter.domain.Message;
import com.drosa.twitter.domain.Timeline;
import com.drosa.twitter.domain.User;
import com.drosa.twitter.repository.UserRepository;
import com.drosa.twitter.util.MessagePrinter;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimelineCommand implements Command {

    private static final Pattern REGEX = Pattern.compile("^\\S+$");

    private final UserRepository userRepository;

    public TimelineCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String commandLine) {
        String userName = commandLine.trim();

        User user = userRepository.getUserOrAdd(userName);
        Timeline timeLine = user.getTimeLine();

        //no se ordena, se obtiene en reversed order y se evita el tiempo de ordenación
        List<Message> messageList = timeLine.getMessages();
        int size = messageList.size();
        for (int i = size - 1; i >= 0; i--)
            MessagePrinter.printMessageForTimeLine(messageList.get(i));

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
