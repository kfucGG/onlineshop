package com.example.onlineshopping.services;


import com.example.onlineshopping.models.Buyer;
import com.example.onlineshopping.models.Item;
import com.example.onlineshopping.models.Orders;
import com.example.onlineshopping.repositories.BuyerRepository;
import com.example.onlineshopping.repositories.ItemRepository;
import com.example.onlineshopping.repositories.OrderRepository;
import org.hibernate.type.OrderedSetType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private BuyerRepository buyerRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    private Orders getBuyerOrder(Buyer buyer){
        Optional<Orders> order = orderRepository.findOrdersByBuyerUsername(buyer.getUsername());
        if(order.isEmpty()){
            Orders newOrder = new Orders();
            newOrder.setBuyer(buyer);
            buyer.setOrder(newOrder);
            orderRepository.save(newOrder);
            return newOrder;
        }
        return order.get();
    }

    public Orders getBuyerOrder(int orderId){
        Optional<Orders> order = orderRepository.findById(orderId);

        return (order.isEmpty()) ? null : order.get();
    }

    public List<Orders> findAll(){
        return orderRepository.findAll();
    }

    @Transactional(readOnly = false)
    public void addOrderToBuyer(Buyer buyer , Item item){
        Orders order = getBuyerOrder(buyer);
        order.getItems().add(item);
        item.getOrders().add(order);
        orderRepository.save(order);
        itemRepository.save(item);
    }
}
