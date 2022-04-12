package com.drosa.twitter.commandService;

import com.drosa.twitter.domain.Message;
import com.drosa.twitter.domain.Timeline;
import com.drosa.twitter.domain.User;
import com.drosa.twitter.repository.UserRepository;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Agrega al repositorio del usuario su mensaje
 */
public class PostCommand implements Command {
    private static final Pattern REGEX = Pattern.compile("^(\\S+) -> (.+)$");

    private final UserRepository userRepository;

    public PostCommand(UserRepository userMessageRepository) {
        this.userRepository = userMessageRepository;
    }

    public boolean execute(String commandLine) {
        Matcher matcher = REGEX.matcher(commandLine);
        matcher.find();

        String userName = matcher.group(1);
        String messageStr = matcher.group(2);

        User user = userRepository.getUserOrAdd(userName);
        Message message = new Message(user, messageStr, Instant.now());

        Timeline timeline = user.getTimeLine();
        timeline.addMessage(message);

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
