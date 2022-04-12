package com.drosa.twitter.commandService;

import com.drosa.twitter.domain.FollowedList;
import com.drosa.twitter.domain.User;
import com.drosa.twitter.repository.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FollowCommand implements Command {
    private static final Pattern REGEX = Pattern.compile("^(\\S+) follows (\\S+)$");

    private final UserRepository userRepository;

    public FollowCommand(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean execute(String commandLine) {
        Matcher matcher = REGEX.matcher(commandLine);
        matcher.find();
        String userName = matcher.group(1);
        String userNameToFollow = matcher.group(2);

        User user = userRepository.getUserOrAdd(userName);
        User userToFollow = userRepository.getUserOrAdd(userNameToFollow);

        FollowedList followedList = user.getFollowedList();
        followedList.addUserToList(userToFollow);

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
