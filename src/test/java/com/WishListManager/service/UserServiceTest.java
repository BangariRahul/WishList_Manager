package com.WishListManager.service;

import com.WishListManager.dao.UserRepository;
import com.WishListManager.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUserTest() {
        // Arrange
        User mockUser = new User();
        mockUser.setPassword("plainPassword");
        String encodedPassword = "encodedPassword";

        when(passwordEncoder.encode(anyString())).thenReturn(encodedPassword);
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User savedUser = userService.createUser(mockUser);

        // Assert
        assertNotNull(savedUser);
        assertEquals(encodedPassword, savedUser.getPassword()); // the password is encoded
        verify(userRepository).save(any(User.class)); // the user is saved
        verify(passwordEncoder).encode("plainPassword"); //  password encoding was called
    }
}

