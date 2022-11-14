package com.example.onlineshopping.repositories;

import com.example.onlineshopping.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Orders, Integer> {

    Optional<Orders> findOrdersByBuyerUsername(String username);
}
