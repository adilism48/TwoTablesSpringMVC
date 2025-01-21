package org.example.twotablesspringmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.twotablesspringmvc.model.User;
import org.example.twotablesspringmvc.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Iterable<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    @Override
    @Transactional
    public User createUser(String name, String email, Date dateOfBirth) {
        return this.userRepository.save(new User(null, name, email, dateOfBirth));
    }

    @Override
    @Transactional
    public void updateUser(Integer id, String name, String email, Date dateOfBirth) {
        this.userRepository.findById(id)
                .ifPresentOrElse(user -> {
                    user.setName(name);
                    user.setEmail(email);
                    user.setDateOfBirth(dateOfBirth);
                }, () -> {
                    throw new NoSuchElementException("User not found");
                });
    }

    @Override
    @Transactional
    public void deleteUser(Integer id) {
        this.userRepository.deleteById(id);
    }
}
