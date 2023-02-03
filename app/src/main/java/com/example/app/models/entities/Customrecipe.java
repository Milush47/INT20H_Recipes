package com.example.app.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "customrecipes")
public class Customrecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //TODO [JPA Buddy] generate columns from DB
}