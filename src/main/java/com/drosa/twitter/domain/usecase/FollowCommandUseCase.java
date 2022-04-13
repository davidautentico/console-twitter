package com.drosa.twitter.domain.usecase;

import com.drosa.twitter.domain.entity.User;
import com.drosa.twitter.domain.repository.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FollowCommandUseCase implements CommandUseCase {
    private static final Pattern REGEX = Pattern.compile("^(\\S+) follows (\\S+)$");

    private final UserRepository userRepository;

    public FollowCommandUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Necesita parsear y extraer el usuario y usuario a seguir
     * @param commandLine
     * @return
     */
    public boolean execute(String commandLine) {
        Matcher matcher = REGEX.matcher(commandLine);
        matcher.find();
        String userName = matcher.group(1);
        String userNameToFollow = matcher.group(2);

        User user = userRepository.getUserOrAdd(userName);
        User userToFollow = userRepository.getUserOrAdd(userNameToFollow);
        user.addFollowed(userToFollow);

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
