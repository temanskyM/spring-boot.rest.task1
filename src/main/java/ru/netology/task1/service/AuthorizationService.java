package ru.netology.task1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.netology.task1.domain.Authorities;
import ru.netology.task1.exception.InvalidCredentials;
import ru.netology.task1.exception.UnauthorizedUser;
import ru.netology.task1.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorizationService {
    private final UserRepository userRepository;

    public List<Authorities> getAuthorities(String user, String password) {
        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}