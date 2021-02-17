package ua.com.conductor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.com.conductor.model.dto.ShoppingCartResponseDto;
import ua.com.conductor.service.MovieSessionService;
import ua.com.conductor.service.ShoppingCartService;
import ua.com.conductor.service.UserService;
import ua.com.conductor.service.dtomappers.ShoppingCartMapper;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final MovieSessionService movieSessionService;
    private final UserService userService;
    private final ShoppingCartMapper mapper;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  MovieSessionService movieSessionService,
                                  UserService userService,
                                  ShoppingCartMapper mapper) {
        this.shoppingCartService = shoppingCartService;
        this.movieSessionService = movieSessionService;
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/movie-sessions")
    public void addMovieSession(Authentication authentication, @RequestParam Long movieSessionId) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String email = details.getUsername();
        shoppingCartService.addSession(movieSessionService.get(movieSessionId),
                userService.findByEmail(email)
                        .orElseThrow(()
                                -> new RuntimeException("Incorrect email or password")));
    }

    @GetMapping("/by-user")
    public ShoppingCartResponseDto getByUser(Authentication authentication) {
        UserDetails details = (UserDetails) authentication.getPrincipal();
        String email = details.getUsername();
        return mapper.toDto(shoppingCartService.getByUser(userService.findByEmail(email)
                .orElseThrow(()
                        -> new RuntimeException("Incorrect email or password"))));
    }
}
