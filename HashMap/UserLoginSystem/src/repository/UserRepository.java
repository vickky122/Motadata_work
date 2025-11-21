package repository;

import model.User;

public interface UserRepository{
    boolean save(User user);
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
