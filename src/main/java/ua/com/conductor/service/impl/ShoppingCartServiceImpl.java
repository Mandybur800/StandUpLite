package ua.com.conductor.service.impl;

import java.util.ArrayList;
import java.util.List;
import ua.com.conductor.dao.ShoppingCartDao;
import ua.com.conductor.dao.TicketDao;
import ua.com.conductor.lib.Inject;
import ua.com.conductor.lib.Service;
import ua.com.conductor.model.MovieSession;
import ua.com.conductor.model.ShoppingCart;
import ua.com.conductor.model.Ticket;
import ua.com.conductor.model.User;
import ua.com.conductor.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;
    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setMovieSession(movieSession);
        ShoppingCart shoppingCartByUser = shoppingCartDao.getByUser(user);
        List<Ticket> tickets = shoppingCartByUser.getTickets();
        ticketDao.add(ticket);
        tickets.add(ticket);
        shoppingCartDao.update(shoppingCartByUser);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.update(shoppingCart);
    }
}
