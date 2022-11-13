package com.example.onlineshopping.controllers;


import com.example.onlineshopping.models.Buyer;
import com.example.onlineshopping.services.BuyerService;
import com.example.onlineshopping.util.BuyerUsernameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {


    private BuyerService buyerService;
    private BuyerUsernameValidator buyerUsernameValidator;

    @Autowired
    public AuthController(BuyerService buyerService, BuyerUsernameValidator buyerUsernameValidator) {
        this.buyerService = buyerService;
        this.buyerUsernameValidator = buyerUsernameValidator;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        model.addAttribute("buyer", new Buyer());
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String saveRegisteredBuyer(@ModelAttribute @Valid Buyer buyer, BindingResult bindingResult){;
        buyerUsernameValidator.validate(buyer, bindingResult);
        if(bindingResult.hasErrors()) return "auth/registration";
        buyerService.saveBuyer(buyer);
        return "redirect:/auth/login";
    }
}
