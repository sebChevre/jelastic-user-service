package ch.sebooom.jelastic.usersservice.domaine;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    void saveUser(User user);

    List<User> getAllUsers();

    Optional<User> findById(Integer userId);

    Optional<User> findUserByLogin(String login);

    Boolean login(String username, String password);
}
