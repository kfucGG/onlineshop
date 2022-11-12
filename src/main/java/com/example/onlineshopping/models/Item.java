package com.example.onlineshopping.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String itemName;
    private int itemPrice;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "item_order", joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"))
    private List<Orders> orders;

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", orders=" + orders +
                '}';
    }
}
