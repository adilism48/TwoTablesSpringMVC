package org.example.twotablesspringmvc.repository;

import org.example.twotablesspringmvc.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
