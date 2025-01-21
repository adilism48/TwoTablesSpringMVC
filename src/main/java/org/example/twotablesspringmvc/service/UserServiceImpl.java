package org.example.twotablesspringmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.twotablesspringmvc.model.User;
import org.example.twotablesspringmvc.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

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
    public User createUser(String name, String email, String dateOfBirth) {
        return this.userRepository.save(new User(null, name, email, dateOfBirth));
    }

    @Override
    public Optional<User> findUserById(int id) {
        return this.userRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateUser(Integer id, String name, String email, String dateOfBirth) {
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
