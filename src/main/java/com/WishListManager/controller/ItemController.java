package com.WishListManager.controller;

import com.WishListManager.entities.Item;
import com.WishListManager.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/wishList")
public class ItemController {


    @Autowired
    private ItemService itemService;

    @PostMapping("/add")
    public String  getUser(@RequestBody Item item , Principal principal){

        return itemService.addItem(item , principal);
    }

    @GetMapping("/get")
    public List<Item> getWishList(Principal principal){
        return itemService.getWishList(principal);
    }

    @DeleteMapping("/delete/{Id}")
    public String deleteItem(@PathVariable("Id") Integer Id , Principal principal ){

        return itemService.deleteItem(Id , principal);
    }

}
