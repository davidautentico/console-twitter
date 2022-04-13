package com.drosa.twitter.domain.usecase;

public interface CommandUseCase {

    boolean execute(String commandLine);

    boolean matches(String input);
}
