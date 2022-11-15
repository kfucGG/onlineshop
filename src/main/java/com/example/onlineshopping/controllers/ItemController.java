package com.example.onlineshopping.controllers;


import com.example.onlineshopping.models.Buyer;
import com.example.onlineshopping.models.Item;
import com.example.onlineshopping.models.Orders;
import com.example.onlineshopping.repositories.ItemRepository;
import com.example.onlineshopping.services.BuyerService;
import com.example.onlineshopping.services.ItemService;
import com.example.onlineshopping.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;
    private BuyerService buyerService;
    private OrderService orderService;
    @Autowired
    public ItemController(ItemService itemService, BuyerService buyerService, OrderService orderService) {
        this.itemService = itemService;
        this.buyerService = buyerService;
        this.orderService = orderService;
    }

    @GetMapping
    public String showAllItems(Model model){
        model.addAttribute("items", itemService.findAllItems());
        return "item/showall";
    }

    @GetMapping("/{id}")
    public String showItemDetails(@PathVariable("id") int id, Model model){
        model.addAttribute("item", itemService.findItemById(id));
        return "item/details";
    }

    @GetMapping("/{id}/inorder")
    public String addItemToOrder(@PathVariable("id") int id){
        orderService.addOrderToBuyer(buyerService.findByUsername(getUserName()).get(), itemService.findItemById(id));
        return "redirect:/items";
    }

    @GetMapping("/m")
    @ResponseBody()
    public String m(){
        return "m";
    }

    @GetMapping("/test")
    public String test(){
        boolean flag = itemService.findItemById(6).isStock();
        System.out.println(flag);
        return "redirect:/items";
    }

    private String getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
