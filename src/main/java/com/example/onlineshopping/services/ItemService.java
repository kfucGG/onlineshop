package com.example.onlineshopping.services;


import com.example.onlineshopping.models.Item;
import com.example.onlineshopping.models.Orders;
import com.example.onlineshopping.repositories.ItemRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public List<Item> findAllItems(){
        return itemRepository.findAll();
    }

    public Item findItemById(int id){
        return itemRepository.findById(id).orElse(null);//TODO
    }

}
