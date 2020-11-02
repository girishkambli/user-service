package com.ing.userservice.core.service;

import com.ing.userservice.api.dto.User;

/**
 * Core User service.
 */
public interface UserService {

    User getUser(long userId);

    void updateUser(User user);
}
