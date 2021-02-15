package ua.com.conductor.service.dtomappers;

import org.springframework.stereotype.Component;
import ua.com.conductor.model.User;
import ua.com.conductor.model.dto.UserRequestDto;
import ua.com.conductor.model.dto.UserResponseDto;

@Component
public class UserMapper {
    public User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        return dto;
    }
}
