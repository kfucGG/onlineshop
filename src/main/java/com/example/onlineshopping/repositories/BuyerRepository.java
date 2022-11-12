package com.example.onlineshopping.repositories;

import com.example.onlineshopping.models.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
}
