package com.WishListManager.service;

import com.WishListManager.dao.ItemRepository;
import com.WishListManager.dao.UserRepository;
import com.WishListManager.entities.Item;
import com.WishListManager.entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ItemServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private Principal principal;

    @InjectMocks
    private ItemService itemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addItemTest() {
        // Arrange
        String email = "user@example.com";
        User user = new User(); // Assume User class is properly instantiated
        user.setEmail(email);
        Item item = new Item(); // Assume Item class is properly instantiated
        item.setName("Test Item");

        when(principal.getName()).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(itemRepository.save(any(Item.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        String result = itemService.addItem(item, principal);

        // Assert
        assertTrue(result.contains("is Added...."));
        verify(itemRepository).save(item); // Ensure item is saved
    }

    @Test
    void getWishListTest() {
        // Arrange
        String email = "user@example.com";
        User user = new User(); // Populate the user's wishlist for the test
        user.setEmail(email);
        Item item = new Item();
        item.setName("Wishlist Item");
        user.setWishlist(Collections.singletonList(item));

        when(principal.getName()).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        List<Item> result = itemService.getWishList(principal);

        // Assert
        assertFalse(result.isEmpty()); // Wishlist should not be empty
        assertEquals("Wishlist Item", result.get(0).getName()); // Check item's name
    }

    @Test
    void deleteItemTest() {
        // Arrange
        int itemId = 1;
        String email = "user@example.com";
        User user = new User();
        user.setUserId(1); // Set user ID for comparison
        Item item = new Item();
        item.setId(itemId);
        item.setUser(user);

        when(principal.getName()).thenReturn(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(itemRepository.findById(itemId)).thenReturn(Optional.of(item));

        // Act
        String result = itemService.deleteItem(itemId, principal);

        // Assert
        assertEquals("Item deleted", result);
        verify(itemRepository).deleteById(itemId); // Ensure item is deleted
    }


}
