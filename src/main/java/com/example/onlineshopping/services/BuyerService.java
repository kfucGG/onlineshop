package com.example.onlineshopping.services;


import com.example.onlineshopping.models.Buyer;
import com.example.onlineshopping.repositories.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BuyerService {

    private BuyerRepository buyerRepository;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository) {
        this.buyerRepository = buyerRepository;
    }

    @Transactional(readOnly = false)
    public void saveBuyer(Buyer buyer){
        buyerRepository.save(buyer);
    }

    public Buyer findBuyerById(int id){
        return buyerRepository.findById(id).orElse(null); //TODO
    }
}
