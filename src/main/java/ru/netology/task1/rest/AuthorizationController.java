package ru.netology.task1.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.netology.task1.domain.Authorities;
import ru.netology.task1.exception.InvalidCredentials;
import ru.netology.task1.exception.UnauthorizedUser;
import ru.netology.task1.service.AuthorizationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthorizationService service;

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }

    @ExceptionHandler(value = InvalidCredentials.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String InvalidCredentialsHandler(InvalidCredentials e) {
        return e.getMessage();
    }

    @ExceptionHandler(value = UnauthorizedUser.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    String UnauthorizedUserHandler(UnauthorizedUser e) {
        System.out.println(e.getMessage());
        return e.getMessage();
    }
}