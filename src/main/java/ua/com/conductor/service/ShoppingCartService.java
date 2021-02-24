package ua.com.conductor.service;

import ua.com.conductor.model.Session;
import ua.com.conductor.model.ShoppingCart;
import ua.com.conductor.model.User;

public interface ShoppingCartService {
    void addSession(Session session, User user);
        
    ShoppingCart getByUser(User user);
        
    void registerNewShoppingCart(User user);
      
    void clear(ShoppingCart shoppingCart);
}
