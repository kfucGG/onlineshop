package com.example.onlineshopping.models;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;

    @OneToOne(mappedBy = "buyer", cascade = CascadeType.ALL)
    private Orders order;


    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", order=" + order +
                '}';
    }
}
