package com.ing.userservice.core.service;

import com.ing.userservice.api.dto.Address;
import com.ing.userservice.api.dto.User;
import com.ing.userservice.core.jpa.entity.AddressEntity;
import com.ing.userservice.core.jpa.entity.UserEntity;
import com.ing.userservice.core.jpa.repository.UserRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(long userId) {

        return userRepository.findById(userId)
            .map(userEntity -> {
                User user = new User();
                updateUser(user, userEntity);
                return user;
            })
            .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public void updateUser(long id, User user) {

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
