package org.example.twotablesspringmvc.service;

import lombok.RequiredArgsConstructor;
import org.example.twotablesspringmvc.model.Phone;
import org.example.twotablesspringmvc.model.User;
import org.example.twotablesspringmvc.repository.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final PhoneRepository phoneRepository;

    @Override
    public Iterable<Phone> findAllByUserId(Integer userId) {
        return this.phoneRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public Phone createPhone(String phoneNumber, User user) {
        return this.phoneRepository.save(new Phone(null, phoneNumber, user));
    }

    @Override
    public Optional<Phone> findPhoneById(int id) {
        return this.phoneRepository.findById(id);
    }

    @Override
    @Transactional
    public void updatePhone(Integer id, String phoneNumber, User user) {
        this.phoneRepository.findById(id)
                .ifPresentOrElse(phone -> {
                    phone.setPhoneNumber(phoneNumber);
                    phone.setUser(user);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deletePhone(Integer id) {
        this.phoneRepository.deleteById(id);
    }
}
