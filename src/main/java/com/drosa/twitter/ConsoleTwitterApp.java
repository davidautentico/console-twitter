package com.drosa.twitter;

import java.util.Scanner;

import com.drosa.twitter.application.CommandApplication;
import com.drosa.twitter.domain.repository.UserRepository;
import com.drosa.twitter.domain.service.printer.MessagePrinter;
import com.drosa.twitter.infraestructure.printer.MessagePrinterImpl;
import com.drosa.twitter.infraestructure.printer.TimeFormatter;
import com.drosa.twitter.infraestructure.repository.UserRepositoryImpl;

public class ConsoleTwitterApp {

    public static void main(String[] args) {

        final UserRepository userRepository = UserRepositoryImpl.getInstance();
        final MessagePrinter messagePrinter = new MessagePrinterImpl(new TimeFormatter());
        final CommandApplication commandApplication = new CommandApplication(userRepository, messagePrinter);

        boolean isCommandProcessedOk;
        do {
            String commandLine = new Scanner(System.in).nextLine();
            isCommandProcessedOk = commandApplication.execute(commandLine);
        } while (isCommandProcessedOk);
    }
}
