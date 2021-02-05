package ua.com.conductor.security;

import java.util.Optional;
import ua.com.conductor.exception.AuthenticationException;
import ua.com.conductor.lib.Inject;
import ua.com.conductor.lib.Service;
import ua.com.conductor.model.User;
import ua.com.conductor.service.ShoppingCartService;
import ua.com.conductor.service.UserService;
import ua.com.conductor.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;
    @Inject
    private ShoppingCartService shoppingCartService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        Optional<User> user = userService.findByEmail(email);
        if (user.isPresent() && user.get().getPassword()
                .equals(HashUtil.hashPassword(password, user.get().getSalt()))) {
            return user.get();
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
