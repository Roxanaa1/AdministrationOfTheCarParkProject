package com.example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncoderTest {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "mariaionescu";
        String encodedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Encoded password: " + encodedPassword);
    }
}
