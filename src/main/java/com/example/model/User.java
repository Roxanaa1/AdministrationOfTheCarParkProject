package com.example.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
enum UserRole{
    ROLE_USER, ROLE_EDITOR
}
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="utilizatori")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilizator")
    private Long id;

    @Column(name = "nume")
    private String name;

    @Column(name = "utilizator", unique = true)
    private String username;

    @Column(name = "parola")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private UserRole role;


}


