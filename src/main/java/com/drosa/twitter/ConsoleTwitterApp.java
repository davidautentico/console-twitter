package com.drosa.twitter;

import java.util.Scanner;

public class ConsoleTwitterApp {

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
        boolean isCommandProcessedOk;
        do {
            String consoleCommand = new Scanner(System.in).nextLine();
            isCommandProcessedOk = server.executeCommand(consoleCommand);
        } while (isCommandProcessedOk);
    }
}
