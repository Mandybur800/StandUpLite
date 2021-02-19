package ua.com.conductor.controllers;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.com.conductor.model.Role;
import ua.com.conductor.model.Roles;
import ua.com.conductor.model.User;
import ua.com.conductor.service.RoleService;
import ua.com.conductor.service.ShoppingCartService;
import ua.com.conductor.service.UserService;

@Controller
public class InjectData {
    private final RoleService roleService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public InjectData(RoleService roleService, UserService userService,
                      ShoppingCartService shoppingCartService) {
        this.roleService = roleService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostConstruct
    private void inject() {
        Role user = new Role();
        user.setRoleName(Roles.USER);
        roleService.add(user);
        Role adminRole = new Role();
        adminRole.setRoleName(Roles.ADMIN);
        roleService.add(adminRole);
        User admin = new User();
        admin.setRoles(List.of(adminRole));
        admin.setEmail("bob@bob.bo");
        admin.setPassword("pass1word");
        userService.add(admin);
        shoppingCartService.registerNewShoppingCart(admin);
    }
}
