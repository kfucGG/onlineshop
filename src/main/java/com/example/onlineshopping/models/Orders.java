package com.example.onlineshopping.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Entity
@Data
public class Orders {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Long random_id;
    @OneToOne()
    @JoinColumn(name = "buyer_id", referencedColumnName = "id")
    private Buyer buyer;

    @ManyToMany(mappedBy = "orders")
    private List<Item> items = new ArrayList<>();
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        this.createdAt = LocalDateTime.now();
        this.random_id = new Random().nextLong(10000000);
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", random_id=" + random_id +
                ", buyer=" + buyer +
                ", items=" + items +
                ", createdAt=" + createdAt +
                '}';
    }
}
