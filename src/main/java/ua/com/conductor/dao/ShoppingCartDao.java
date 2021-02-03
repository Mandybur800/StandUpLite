package ua.com.conductor.dao;

import ua.com.conductor.model.ShoppingCart;
import ua.com.conductor.model.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);
        
    ShoppingCart getByUser(User user);
        
    void update(ShoppingCart shoppingCart);
}
