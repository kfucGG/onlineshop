package com.example.onlineshopping.repositories;

import com.example.onlineshopping.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
