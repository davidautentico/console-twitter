package com.drosa.twitter;

import com.drosa.twitter.commandService.CommandService;
import com.drosa.twitter.repository.UserRepository;

/**
 * Inicializa los repositorios que serán usados por el servicio de comandandos para ejecutar las órdenes
 */
public class Server {

    private final UserRepository userRepository;
    private final CommandService commandService;

    public Server() {
        userRepository = UserRepository.getInstance();
        commandService = new CommandService(userRepository);
    }

    public boolean executeCommand(String command) {
        boolean isCommandProcessed = commandService.execute(command);
        return true;
    }

    public void start() {
        System.out.println("**** TWITTER CONSOLE STARTED ****");
    }

    public void stop() {
        userRepository.clear();
    }
}
