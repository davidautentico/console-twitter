package com.drosa.twitter.commandService;

public interface Command {

    boolean execute(String commandLine);

    boolean matches(String input);
}
