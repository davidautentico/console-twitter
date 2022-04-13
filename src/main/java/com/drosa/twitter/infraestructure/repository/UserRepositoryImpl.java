package com.drosa.twitter.infraestructure.repository;

import com.drosa.twitter.domain.entity.User;
import com.drosa.twitter.domain.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Estructura caché que almacena los usuarios del sistema
 */
public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> users;

    private static UserRepositoryImpl _instance = null;

    protected UserRepositoryImpl() {
        users = new HashMap();
    }

    public static UserRepositoryImpl getInstance() {
        if (_instance == null) {
            _instance = new UserRepositoryImpl();
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
