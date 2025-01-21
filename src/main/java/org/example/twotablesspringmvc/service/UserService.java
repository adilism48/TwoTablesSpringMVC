package org.example.twotablesspringmvc.service;

import org.example.twotablesspringmvc.model.User;

import java.sql.Date;

public interface UserService {

    Iterable<User> findAllUsers();

    User createUser(String name, String email, Date dateOfBirth);

    void updateUser(Integer id, String name, String email, Date dateOfBirth);

    void deleteUser(Integer id);
}