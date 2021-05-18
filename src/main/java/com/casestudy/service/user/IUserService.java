package com.casestudy.service.user;

import com.casestudy.model.User;
import com.casestudy.service.IGeneralService;

import java.util.Optional;

public interface IUserService extends IGeneralService<User> {

    Optional<User> findByUsername(String username);

//    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);

    Iterable<User> findUsersByNameContaining(String user_name);
    void save(User user);
}
