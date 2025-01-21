package org.example.twotablesspringmvc.service;

import org.example.twotablesspringmvc.model.Phone;
import org.example.twotablesspringmvc.model.User;

public interface PhoneService {

    Iterable<Phone> findAllPhones();

    Phone createPhone(String phoneNumber, User user);

    void updatePhone(Integer id, String phoneNumber, User user);

    void deletePhone(Integer id);
}
