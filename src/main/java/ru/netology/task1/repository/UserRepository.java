package ru.netology.task1.repository;

import org.springframework.stereotype.Repository;
import ru.netology.task1.domain.Authorities;
import ru.netology.task1.exception.InvalidCredentials;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    static Map<String, String> users = new HashMap<>();
    static Map<String, List<Authorities>> permission = new HashMap<>();

    static {
        users.put("user1", "password1");
        users.put("user2", "password2");
        users.put("user3", "password3");

        permission.put("user1", new ArrayList<>());
        permission.put("user2", List.of(Authorities.READ, Authorities.WRITE));
        permission.put("user3", List.of(Authorities.READ, Authorities.WRITE, Authorities.DELETE));
    }

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (users.containsKey(user)) {
            String passwordFromDb = users.get(user);
            if (passwordFromDb.equals(password))
                return permission.get(user);
        }
        throw new InvalidCredentials("InvalidCredentials");
    }
}
