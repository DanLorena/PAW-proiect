package com.magazinonline.proiect.bd;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter // Lombok va genera automat setterele pentru toate câmpurile
@Entity
public class Category {

    private final String description;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Lombok va genera un getter pentru acest câmp

    private String name;
    private String imageName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Product> products; // Lista de produse ale categoriei

    // Constructor implicit
    public Category(String description) {
        this.description = description;
    }

    // Constructor cu parametri
    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Category() {
        this.description = "";

    }


    // Metodă toString pentru a vizualiza mai ușor obiectul Category
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageName='" + imageName + '\'' +
                ", products=" + products +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
