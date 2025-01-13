package com.magazinonline.proiect.bd;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public void addToCart(Long productId, int quantity) {
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}
