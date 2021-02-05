package ua.com.conductor.dao;

import java.util.List;
import ua.com.conductor.model.Order;
import ua.com.conductor.model.User;

public interface OrderDao {
    Order add(Order order);

    List<Order> getOrdersHistory(User user);
}
