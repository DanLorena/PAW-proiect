package com.magazinonline.proiect.bd;


import com.magazinonline.proiect.User;
import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public void setProductId(Long productId) {
    }

    public void setQuantity(int quantity) {
    }

    // Getters and Setters
}


