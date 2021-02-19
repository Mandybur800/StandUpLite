package ua.com.conductor.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.conductor.model.User;
import ua.com.conductor.service.RoleService;
import ua.com.conductor.service.ShoppingCartService;
import ua.com.conductor.service.UserService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final RoleService roleService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public AuthenticationServiceImpl(UserService userService,
                                     RoleService roleService,
                                     ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(List.of(roleService.getRoleByName("USER")));
        userService.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }
}
