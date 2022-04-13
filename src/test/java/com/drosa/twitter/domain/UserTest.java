package com.drosa.twitter.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.drosa.twitter.domain.entity.User;

class UserTest {

    private static String USER_NAME = "David";

    private User user = new User(USER_NAME);

    @BeforeEach
    public void onBefore(){
        //
    }

    @Test
    void getUserName_shouldReturnExpectedValue(){
        assertEquals(USER_NAME, user.getName());
    }

    @Test
    void getTimeline_shouldReturnNotNullTimeLine(){
        assertNotNull(user.getTimeLine());
    }

    @Test
    void getFollowedList_shouldReturnNotNull(){
        assertNotNull(user.getFollowedList());
    }
}