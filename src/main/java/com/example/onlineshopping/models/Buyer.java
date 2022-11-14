package com.example.onlineshopping.models;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Buyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "can not be blank")
    private String username;

    @NotBlank(message = "can not be blank")
    private String password;

    @OneToOne(mappedBy = "buyer")
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
