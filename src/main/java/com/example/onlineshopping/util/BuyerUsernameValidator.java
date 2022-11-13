package com.example.onlineshopping.util;

import com.example.onlineshopping.models.Buyer;
import com.example.onlineshopping.services.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class BuyerUsernameValidator implements Validator {

    private BuyerService buyerService;

    @Autowired
    public BuyerUsernameValidator(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Buyer.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Buyer buyer = (Buyer) target;
        if(!buyerService.findByUsername(buyer.getUsername()).isEmpty()){
            errors.rejectValue("username", "", "this username is exists");
        }
    }
}
