package ua.com.conductor.service.dtomappers;

import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import ua.com.conductor.model.Order;
import ua.com.conductor.model.Ticket;
import ua.com.conductor.model.dto.OrderResponseDto;

@Component
public class OrderMapper {
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setDate(order.getOrderDate().toString());
        dto.setEmail(order.getUser().getEmail());
        dto.setTicketsId(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return dto;
    }
}
