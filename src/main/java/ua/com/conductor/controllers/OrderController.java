package ua.com.conductor.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.conductor.model.dto.OrderResponseDto;
import ua.com.conductor.service.OrderService;
import ua.com.conductor.service.ShoppingCartService;
import ua.com.conductor.service.UserService;
import ua.com.conductor.service.dtomappers.OrderMapper;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final OrderService orderService;
    private final OrderMapper mapper;

    @Autowired
    public OrderController(ShoppingCartService shoppingCartService,
                           UserService userService,
                           OrderService orderService,
                           OrderMapper mapper) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping("/complete")
    public OrderResponseDto completeOrder(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String email = details.getUsername();
        return mapper.toDto(orderService
                .completeOrder(shoppingCartService
                        .getByUser(userService.findByEmail(email)
                                .orElseThrow(()
                                        -> new RuntimeException("Incorrect email or password")))));
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersForUser(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String email = details.getUsername();
        return orderService.getOrdersHistory(userService.findByEmail(email)
                .orElseThrow(()
                        -> new RuntimeException("Incorrect email or password")))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
