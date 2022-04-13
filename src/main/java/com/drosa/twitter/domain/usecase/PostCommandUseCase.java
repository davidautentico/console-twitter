package com.drosa.twitter.domain.usecase;

import com.drosa.twitter.domain.entity.Message;
import com.drosa.twitter.domain.entity.User;
import com.drosa.twitter.domain.repository.UserRepository;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Agrega al repositorio del usuario su mensaje
 */
public class PostCommandUseCase implements CommandUseCase {
    private static final Pattern REGEX = Pattern.compile("^(\\S+) -> (.+)$");

    private final UserRepository userRepository;

    public PostCommandUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Extrae del commandline el usuario y el mensaje y lo agrega
     * @param commandLine
     * @return
     */
    public boolean execute(String commandLine) {
        Matcher matcher = REGEX.matcher(commandLine);
        matcher.find();

        String userName = matcher.group(1);
        String messageStr = matcher.group(2);

        User user = userRepository.getUserOrAdd(userName);
        Message message = new Message(user, messageStr, Instant.now());
        user.addMessage(message);

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
