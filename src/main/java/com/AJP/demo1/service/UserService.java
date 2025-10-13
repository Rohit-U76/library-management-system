package com.AJP.demo1.service;

import com.AJP.demo1.entity.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    User saveUser(User user);
    void deleteUserById(Long id);
}