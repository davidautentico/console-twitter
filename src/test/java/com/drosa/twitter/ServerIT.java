package com.drosa.twitter;

import com.drosa.twitter.domain.User;
import com.drosa.twitter.repository.UserRepository;
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

    private Server server;
    private UserRepository userRepository;

    @BeforeAll
    public void setup() throws NoSuchFieldException, IllegalAccessException {

        server = new Server();
        Field privateField = Server.class.getDeclaredField("userRepository");
        privateField.setAccessible(true);
        userRepository = (UserRepository) privateField.get(server);

        System.out.println("*** Server setup ***");
        System.out.println("userRepository size <0" + userRepository.getSize() + ">");
    }

    @Test
    void whenRunningPostAndFollows_shouldExistsExpectedItems() {
        server.executeCommand(ALICE_POST);

        assertEquals(1, userRepository.getSize());

        User userAlice = userRepository.getUserOrAdd(ALICE);

        assertEquals(1, userAlice.getTimeLine().getMessages().size());

        server.executeCommand(BOB_POST_1);
        server.executeCommand(BOB_POST_2);

        assertEquals(2, userRepository.getSize());

        User userBob = userRepository.getUserOrAdd(BOB);

        assertEquals(2, userBob.getTimeLine().getMessages().size());

        server.executeCommand(CHARLIE_POST);

        assertEquals(3, userRepository.getSize());

        User userCharlie = userRepository.getUserOrAdd(CHARLIE);

        assertEquals(1, userCharlie.getTimeLine().getMessages().size());

        server.executeCommand(CHARLIE_FOLLOWS_ALICE);
        server.executeCommand(CHARLIE_FOLLOWS_BOB);

        assertEquals(2, userCharlie.getFollowedList().getUserList().size());
    }

}
