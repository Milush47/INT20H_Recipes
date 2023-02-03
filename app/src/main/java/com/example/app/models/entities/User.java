package com.example.app.models.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", length = 32)
    private String username;

    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Column(name = "surname", nullable = false, length = 32)
    private String surname;

    @Column(name = "email", nullable = false, length = 32)
    private String email;
}
