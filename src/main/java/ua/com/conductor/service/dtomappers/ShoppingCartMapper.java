package ua.com.conductor.service.dtomappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ua.com.conductor.model.ShoppingCart;
import ua.com.conductor.model.Ticket;
import ua.com.conductor.model.dto.ShoppingCartResponseDto;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto toDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto dto = new ShoppingCartResponseDto();
        dto.setUserId(shoppingCart.getId());
        dto.setTicketsId(shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
