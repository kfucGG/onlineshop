package com.example.onlineshopping.services;


import com.example.onlineshopping.models.Buyer;
import com.example.onlineshopping.models.Orders;
import com.example.onlineshopping.repositories.BuyerRepository;
import com.example.onlineshopping.security.BuyerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Order;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BuyerService implements UserDetailsService {

    private BuyerRepository buyerRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public BuyerService(BuyerRepository buyerRepository, PasswordEncoder passwordEncoder) {
        this.buyerRepository = buyerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = false)
    public void saveBuyer(Buyer buyer) {
        buyer.setPassword(passwordEncoder.encode(buyer.getPassword()));
        buyerRepository.save(buyer);
    }

    public Buyer findBuyerById(int id){
        return buyerRepository.findById(id).orElse(null); //TODO
    }

    public Optional<Buyer> findByUsername(String username){
        Optional<Buyer> buyer = buyerRepository.findBuyerByUsername(username);
        return buyer;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Buyer> buyer = buyerRepository.findBuyerByUsername(username);
        if(buyer.isEmpty()) throw new UsernameNotFoundException("username not found");

        return new BuyerDetails(buyer.get());
    }

}
