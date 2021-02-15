package ua.com.conductor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.conductor.model.dto.UserRequestDto;
import ua.com.conductor.security.AuthenticationService;
import ua.com.conductor.service.dtomappers.UserMapper;

@RestController
@RequestMapping
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final UserMapper mapper;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService,
                                    UserMapper mapper) {
        this.authenticationService = authenticationService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public void registration(@RequestBody UserRequestDto dto) {
        authenticationService.register(dto.getEmail(), dto.getEmail());
    }
}
