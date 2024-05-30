package com.example.controller;
import com.example.UserNotFoundException;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(path = "/jpa/users")
public class UserController
{
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") User user, Model model)
    {
        if (userService.findUserByUsername(user.getUsername()) != null) {
            model.addAttribute("user", user);
            model.addAttribute("registrationError", "Username already exists.");
            return "register";
        }
        userService.createUser(user);
        return "redirect:/jpa/users/register?success";
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
