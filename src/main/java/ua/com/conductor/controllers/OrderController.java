package ua.com.conductor.controllers;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public OrderResponseDto completeOrder(@RequestParam Long userId) {
        return mapper.toDto(orderService
                .completeOrder(shoppingCartService.getByUser(userService.get(userId))));
    }

    @GetMapping
    public List<OrderResponseDto> getOrdersForUser(@RequestParam Long userId) {
        return orderService.getOrdersHistory(userService.get(userId))
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
