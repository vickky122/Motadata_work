package service;

import model.User;
import repository.UserRepository;
import security.PasswordEncoder;

public class AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository repo, PasswordEncoder encoder){
        this.userRepository = repo;
        this.passwordEncoder = encoder;
    }

    // Register user -> validates, checks duplicate, hashes password
    public boolean register(String username,String password){
        if(username == null || username.isBlank()) return false;
        if(password ==null || password.isBlank()) return false;
        if(userRepository.existsByUsername(username)) return false;

        String hashed = passwordEncoder.encode(password);
        User user = new User(username, hashed);
        return userRepository.save(user);
    }

    // Authenticate user -> fetch stored hash and compare
    public boolean login(String username, String password){
        User user = userRepository.findByUsername(username);
        if(user == null) return false;
        return passwordEncoder.matches(password, user.getPasswordHash());
    }
}
