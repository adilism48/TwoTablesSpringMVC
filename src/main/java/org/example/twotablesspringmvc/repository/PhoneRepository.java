package org.example.twotablesspringmvc.repository;

import org.example.twotablesspringmvc.model.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Integer> {
    Iterable<Phone> findByUserId(Integer userId);
}
