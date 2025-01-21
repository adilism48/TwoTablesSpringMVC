package org.example.twotablesspringmvc.service;

import org.example.twotablesspringmvc.model.Phone;
import org.example.twotablesspringmvc.model.User;

import java.util.Optional;

public interface PhoneService {

    Iterable<Phone> findAllByUserId(Integer userId);

    Phone createPhone(String phoneNumber, User user);

    Optional<Phone> findPhoneById(int id);

    void updatePhone(Integer id, String phoneNumber, User user);

    void deletePhone(Integer id);
}
