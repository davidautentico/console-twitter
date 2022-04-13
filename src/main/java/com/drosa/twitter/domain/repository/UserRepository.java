package com.drosa.twitter.domain.repository;

import com.drosa.twitter.domain.entity.User;

public interface UserRepository {

  User getUserOrAdd(String key);

  int getSize();
}
