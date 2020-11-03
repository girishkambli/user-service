package com.ing.userservice.core.service;

import com.ing.userservice.api.dto.User;

/**
 * Core User service.
 */
public interface UserService {

    /**
     * Get user.
     * @param userId user id.
     * @return User if found else throws {@link javax.persistence.EntityNotFoundException}
     */
    User getUser(long userId);

    /**
     * Added just for testing circuit breaker.
     * @param userId user id
     * @param triggerCircuitBreaker Set to {@code true} in order to simulate tripping of circuit breaker
     * @return User, can return a dummy user if circuit breaker is tripped off.
     */
    User getUser(long userId, boolean triggerCircuitBreaker);

    /**
     * Update user
     * @param user User to update
     */
    void updateUser(User user);
}
