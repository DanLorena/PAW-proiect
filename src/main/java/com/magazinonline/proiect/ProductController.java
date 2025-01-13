package com.magazinonline.proiect;

import com.magazinonline.proiect.ProductService;
import com.magazinonline.proiect.bd.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/category/{categoryId}")
    public String getProductsByCategory(@PathVariable Long categoryId, Model model) {
        // Obține produsele din categorie
        List<Product> products = productService.getProductsByCategory(categoryId);

        // Adaugă produsele în modelul Thymeleaf
        model.addAttribute("products", products);

        // Returnează numele template-ului Thymeleaf
        return "category"; // Numele fișierului Thymeleaf: category.html
    }

}

