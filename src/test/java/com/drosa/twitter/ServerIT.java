package com.drosa.twitter;

import com.drosa.twitter.application.CommandApplication;
import com.drosa.twitter.domain.entity.User;
import com.drosa.twitter.domain.repository.UserRepository;
import com.drosa.twitter.domain.service.printer.MessagePrinter;
import com.drosa.twitter.infraestructure.printer.MessagePrinterImpl;
import com.drosa.twitter.infraestructure.printer.TimeFormatter;
import com.drosa.twitter.infraestructure.repository.UserRepositoryImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Clase con test de integración
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServerIT {

    private final String ALICE = "Alice";
    private final String BOB = "Bob";
    private final String CHARLIE = "Charlie";
    private final String ALICE_POST = "Alice -> I love the weather today";
    private final String BOB_POST_1 = "Bob -> Damn! We lost!";
    private final String BOB_POST_2 = "Bob -> Good game though.";
    private final String ALICE_TIMELINE = "Alice";
    private final String BOB_TIMELINE = "Bob";
    private final String CHARLIE_POST = "Charlie -> I'm in New York today! Anyone want to have a coffee?";
    private final String CHARLIE_FOLLOWS_ALICE = "Charlie follows Alice";
    private final String CHARLIE_FOLLOWS_BOB = "Charlie follows Bob";
    private final String CHARLIE_WALL = "Charlie wall";

    private CommandApplication commandApplication;
    private UserRepository userRepository;
    private MessagePrinter messagePrinter;

    @BeforeAll
    public void setup() throws NoSuchFieldException, IllegalAccessException {
        userRepository = UserRepositoryImpl.getInstance();
        messagePrinter = new MessagePrinterImpl(new TimeFormatter());
        commandApplication = new CommandApplication(userRepository, messagePrinter );

        System.out.println("*** Server setup ***");
        System.out.println("userRepositoryImpl size <0" + userRepository.getSize() + ">");
    }

    @Test
    void whenRunningPostAndFollows_shouldExistsExpectedItems() {
        commandApplication.execute(ALICE_POST);

        assertEquals(1, userRepository.getSize());

        User userAlice = userRepository.getUserOrAdd(ALICE);

        assertEquals(1, userAlice.getTimeLine().size());

        commandApplication.execute(BOB_POST_1);
        commandApplication.execute(BOB_POST_2);

        assertEquals(2, userRepository.getSize());

        User userBob = userRepository.getUserOrAdd(BOB);

        assertEquals(2, userBob.getTimeLine().size());

        commandApplication.execute(CHARLIE_POST);

        assertEquals(3, userRepository.getSize());

        User userCharlie = userRepository.getUserOrAdd(CHARLIE);

        assertEquals(1, userCharlie.getTimeLine().size());

        commandApplication.execute(CHARLIE_FOLLOWS_ALICE);
        commandApplication.execute(CHARLIE_FOLLOWS_BOB);

        assertEquals(2, userCharlie.getFollowedList().size());
    }

}
