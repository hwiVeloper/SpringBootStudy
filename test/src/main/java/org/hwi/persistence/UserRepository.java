package org.hwi.persistence;

import org.springframework.data.repository.CrudRepository;

import org.hwi.domain.User;

public interface UserRepository extends CrudRepository<User, String>{

}