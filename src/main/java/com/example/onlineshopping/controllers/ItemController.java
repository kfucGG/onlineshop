package com.example.onlineshopping.controllers;


import com.example.onlineshopping.repositories.ItemRepository;
import com.example.onlineshopping.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public String showAllItems(Model model){
        model.addAttribute("items", itemService.findAllItems());
        return "item/showall";
    }
}
