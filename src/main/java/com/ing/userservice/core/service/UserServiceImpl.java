package com.ing.userservice.core.service;

import com.ing.userservice.api.dto.Address;
import com.ing.userservice.api.dto.Gender;
import com.ing.userservice.api.dto.User;
import com.ing.userservice.core.jpa.entity.AddressEntity;
import com.ing.userservice.core.jpa.entity.UserEntity;
import com.ing.userservice.core.jpa.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(long userId) {
        return getUser(userId, false);
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallBackGetUser", ignoreExceptions = {
        EntityNotFoundException.class})
    public User getUser(long userId, boolean triggerCircuitBreaker) {

        if (triggerCircuitBreaker) {
            throw new RuntimeException("Simulated error to trigger circuit breaker.");
        }

        return userRepository.findById(userId)
            .map(userEntity -> {
                User user = new User();
                updateUser(user, userEntity);
                return user;
            })
            .orElseThrow(() -> new EntityNotFoundException("User Not Found: " + userId));
    }

    public User fallBackGetUser(long userId, boolean triggerCircuitBreaker, Throwable throwable) {

        log.error("In fallBackGetUser, exception is {}", throwable.getMessage());

        //Dummy user
        User user = new User();
        user.setFirstName("Circuit");
        user.setLastName("Breaker");
        user.setId("0");
        user.setTitle("Mr.");
        user.setGender(Gender.MALE);
        Address address = new Address();
        user.setAddress(address);
        return user;
    }

    @Override
    @Transactional
    public void updateUser(User user) {

        UserEntity userEntity = userRepository.findById(Long.valueOf(user.getId()))
            .orElseThrow(EntityNotFoundException::new);
        updateUserEntity(user, userEntity);
        userRepository.save(userEntity);
    }

    private void updateUser(User user, UserEntity userEntity) {

        user.setId(userEntity.getId().toString());
        user.setTitle(userEntity.getTitle());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        Address address = new Address();
        updateAddress(address, userEntity.getAddress());
        user.setAddress(address);
        user.setGender(userEntity.getGender());
    }

    private void updateAddress(Address address, AddressEntity addressEntity) {

        address.setStreet(addressEntity.getStreet());
        address.setCity(addressEntity.getCity());
        address.setState(addressEntity.getState());
        address.setPostcode(addressEntity.getPostcode());
    }

    private void updateUserEntity(User user, UserEntity userEntity) {

        userEntity.setTitle(user.getTitle());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setGender(user.getGender());
        updateAddressEntity(user.getAddress(), userEntity.getAddress());
    }

    private void updateAddressEntity(Address address, AddressEntity addressEntity) {

        addressEntity.setStreet(address.getStreet());
        addressEntity.setCity(address.getCity());
        addressEntity.setState(address.getState());
        addressEntity.setPostcode(address.getPostcode());
    }


}
