package com.drosa.twitter.repository;

import com.drosa.twitter.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Estructura caché que almacena los usuarios del sistema
 */
public class UserRepository {
    private final Map<String, User> users;

    private static UserRepository _instance = null;

    protected UserRepository() {
        users = new HashMap();
    }

    public static UserRepository getInstance() {
        if (_instance == null) {
            _instance = new UserRepository();
        }
        return _instance;
    }

    public User getUserOrAdd(String key) {
        Optional<User> userFromRepo = Optional.ofNullable(users.get(key));
        User user;
        if (!userFromRepo.isPresent()) {
            user = new User(key);
            users.put(key, user);
        } else {
            user = userFromRepo.get();
        }

        return user;
    }

    public int getSize() {
        return this.users.size();
    }


    public void clear() {
        users.clear();
    }
}
