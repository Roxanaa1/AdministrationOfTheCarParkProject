package com.example.service;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(User userDetails, Long id)
    {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setUsername(userDetails.getUsername());
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
            user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
    }
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> findUserById(Long id)
    {
        return userRepository.findById(id);
    }
    public User findUserByUsername(String username)
    {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
