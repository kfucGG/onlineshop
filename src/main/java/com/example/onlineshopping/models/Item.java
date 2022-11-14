package com.example.onlineshopping.models;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String itemName;
    private int itemPrice;

    @ManyToMany()
    @JoinTable(name = "item_order", joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Orders> orders;

    public void addInOrder(Orders order){
        if(this.orders == null){
            orders = new ArrayList<>();
        }
        orders.add(order);
    }

    public void addOrder(Orders order){
        if (orders.isEmpty()){
            this.orders = new ArrayList<>();
        }
        orders.add(order);
    }
}
