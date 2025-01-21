package org.example.twotablesspringmvc.service;

import org.example.twotablesspringmvc.model.User;

import java.sql.Date;
import java.util.Optional;

public interface UserService {

    Iterable<User> findAllUsers();

    User createUser(String name, String email, Date dateOfBirth);

    Optional<User> findUserById(int id);

    void updateUser(Integer id, String name, String email, Date dateOfBirth);

    void deleteUser(Integer id);
}