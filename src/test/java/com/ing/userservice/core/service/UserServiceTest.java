package com.ing.userservice.core.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ing.userservice.api.dto.Address;
import com.ing.userservice.api.dto.User;
import com.ing.userservice.core.jpa.entity.AddressEntity;
import com.ing.userservice.core.jpa.entity.UserEntity;
import com.ing.userservice.core.jpa.repository.UserRepository;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setup() {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testThatGetUserCall_QueriesDB() {
        UserEntity user = mock(UserEntity.class);
        when(user.getAddress()).thenReturn(mock(AddressEntity.class));

        when(userRepository.findById(0L)).thenReturn(Optional.of(
            user));
        userService.getUser(0);
        verify(userRepository).findById(0L);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testWhenUserDoesNotExistThenGetFails() {
        when(userRepository.findById(0L)).thenReturn(Optional.empty());
        userService.getUser(0);
    }

    @Test
    public void testThatPutUserCall_PostsToDB() {
        User user = mock(User.class);
        when(user.getAddress()).thenReturn(mock(Address.class));
        when(user.getId()).thenReturn("0");
        UserEntity userEntity = mock(UserEntity.class);
        when(userRepository.findById(0L)).thenReturn(Optional.of(userEntity));
        when(userEntity.getAddress()).thenReturn(mock(AddressEntity.class));

        userService.updateUser(user);
        verify(userRepository).save(userEntity);
    }

    @Test(expected = EntityNotFoundException.class)
    public void testWhenUserDoesNotExistThenPutFails() {
        User user = mock(User.class);
        when(user.getId()).thenReturn("0");
        when(userRepository.findById(0L)).thenReturn(Optional.empty());

        userService.updateUser(user);
    }

}
