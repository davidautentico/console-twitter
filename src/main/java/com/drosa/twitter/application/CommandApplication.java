package com.drosa.twitter.application;

import com.drosa.twitter.domain.usecase.CommandUseCase;
import com.drosa.twitter.domain.repository.UserRepository;
import com.drosa.twitter.domain.usecase.FollowCommandUseCase;
import com.drosa.twitter.domain.service.printer.MessagePrinter;
import com.drosa.twitter.domain.usecase.PostCommandUseCase;
import com.drosa.twitter.domain.usecase.TimelineCommandUseCase;
import com.drosa.twitter.domain.usecase.WallCommandUseCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandApplication {

    private final List<CommandUseCase> commands;

    private final UserRepository userRepository;

    private final MessagePrinter messagePrinter;

    public CommandApplication(UserRepository userRepository, MessagePrinter messagePrinter) {
        this.userRepository = userRepository;
        this.messagePrinter = messagePrinter;

        //add predefined commands
        commands = new ArrayList<>();
        commands.add(new PostCommandUseCase(userRepository));
        commands.add(new FollowCommandUseCase(userRepository));
        commands.add(new TimelineCommandUseCase(userRepository, messagePrinter));
        commands.add(new WallCommandUseCase(userRepository, messagePrinter));
    }

    public boolean execute(String commandLine) {
        try {
            CommandUseCase command = getCommand(commandLine);
            command.execute(commandLine);
        } catch (InvalidCommandException e) {
            System.err.println("Error invalid command <" + commandLine + ">");
            return false;
        }
        return true;
    }

    private CommandUseCase getCommand(String commandLine) throws InvalidCommandException {
        Optional<CommandUseCase> command = commands.stream()
                .filter(c -> c.matches(commandLine))
                .findFirst();

        if (command.isPresent()) return command.get();

        throw new InvalidCommandException();
    }
}
