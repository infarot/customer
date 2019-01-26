package com.dao;

import com.entities.User;

public interface UserDAO {
    void saveUser(User user);

    User findByUserName(String username);
}
