package com.example.controller;
import com.example.UserNotFoundException;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/jpa/users")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/getUserById{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {
        User user = userService.findUserById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return ResponseEntity.ok(user);
    }
    @PostMapping("/createUser")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User newUser = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
    @PutMapping("/updateUser{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails)
    {
        User updatedUser = userService.updateUser(userDetails, id);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/deleteUser{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id)
    {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
