package com.example.onlineshopping.controllers;


import com.example.onlineshopping.services.BuyerService;
import com.example.onlineshopping.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adminpage")
public class AdminController {

    private BuyerService buyerService;
    private OrderService orderService;

    @Autowired
    public AdminController(BuyerService buyerService, OrderService orderService) {
        this.buyerService = buyerService;
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String showAllOrders(Model model){
        model.addAttribute("orders", orderService.findAll());
        return "admin/orders";
    }

    @GetMapping("/orders/{id}")
    public String showOrderDetails(Model model, @PathVariable("id") int id){
        model.addAttribute("order", orderService.getBuyerOrder(id));
        model.addAttribute("items", orderService.getBuyerOrder(id).getItems());
        return "admin/orderdetails";
    }
}
