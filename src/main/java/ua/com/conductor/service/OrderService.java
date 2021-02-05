package ua.com.conductor.service;

import java.util.List;
import ua.com.conductor.model.Order;
import ua.com.conductor.model.ShoppingCart;
import ua.com.conductor.model.User;

public interface OrderService {
    Order completeOrder(ShoppingCart shoppingCart);

    List<Order> getOrdersHistory(User user);
}
