package com.WishListManager.service;

import com.WishListManager.dao.ItemRepository;
import com.WishListManager.dao.UserRepository;
import com.WishListManager.entities.Item;
import com.WishListManager.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    public String addItem(Item item, Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).get();
              item.setUser(user);
        itemRepository.save(item);
        return "Item" + item.getName() + " is Added...." ;
    }

    public List<Item> getWishList(Principal principal) {
        User user = userRepository.findByEmail(principal.getName()).get();
        return user.getWishlist() ;
    }

    public String deleteItem(Integer id, Principal principal) {

        User user = userRepository.findByEmail(principal.getName()).get();

        Item item = itemRepository.findById(id).get();

        if (item.getUser().getUserId() == user.getUserId()) {
            itemRepository.deleteById(id);
            return "Item deleted";
        }
        return "you cannot delete this item";
    }
    }
