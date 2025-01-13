package com.magazinonline.proiect.cos;



import com.magazinonline.proiect.bd.Product;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> produse = new ArrayList<>();

    private Double total = 0.0; // Totalul coșului

    // Getteri și setteri
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProduse() {
        return produse;
    }

    public void setProduse(List<Product> produse) {
        this.produse = produse;
        recalculareTotal(); // Recalculăm totalul când setăm lista
    }

    public Double getTotal() {
        return total;
    }

    public void adaugaProdus(Product produs) {
        this.produse.add(produs);
        recalculareTotal(); // Actualizăm totalul
    }

    public void stergeProdus(Product produs) {
        this.produse.remove(produs);
        recalculareTotal(); // Actualizăm totalul
    }

    // Metodă privată pentru recalcularea totalului
    private void recalculareTotal() {
        this.total = produse.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
}

