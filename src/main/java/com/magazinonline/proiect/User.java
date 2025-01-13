package com.magazinonline.proiect;



import com.magazinonline.proiect.bd.Orders;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Orders> orders;

    // Getters and Setters
}

