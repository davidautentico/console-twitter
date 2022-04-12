package com.drosa.twitter.commandService;

import com.drosa.twitter.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandService {

    private final List<Command> commands;

    public CommandService(final UserRepository userRepository) {

        //add predefined commands
        commands = new ArrayList<>();
        commands.add(new PostCommand(userRepository));
        commands.add(new FollowCommand(userRepository));
        commands.add(new TimelineCommand(userRepository));
        commands.add(new WallCommand(userRepository));
    }

    public boolean execute(String commandLine) {
        try {
            Command command = getCommand(commandLine);
            command.execute(commandLine);
        } catch (InvalidCommandException e) {
            System.err.println("Error invalid command <" + commandLine + ">");
            return false;
        }
        return true;
    }

    private Command getCommand(String commandLine) throws InvalidCommandException {
        Optional<Command> command = commands.stream()
                .filter(c -> c.matches(commandLine))
                .findFirst();

        if (command.isPresent()) return command.get();

        throw new InvalidCommandException();
    }
}
