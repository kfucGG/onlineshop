package com.example.onlineshopping.repositories;

import com.example.onlineshopping.models.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {

    Optional<Buyer> findBuyerByUsername(String username);
}
