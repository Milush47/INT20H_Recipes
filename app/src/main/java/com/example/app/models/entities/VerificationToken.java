package com.example.app.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "verification_token")
public class VerificationToken {
    private final static int EXPIRATION = 60 * 24;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="token")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="expiry_date")
    private Date expiryDate;

    public VerificationToken( String token,  User user) {
        super();
        Calendar calendar = Calendar.getInstance();

        this.token          = token;
        this.user           = user;
        this.createdDate    = new Date(calendar.getTime().getTime());
        this.expiryDate     = calculateExpiryDate();
    }

    private Date calculateExpiryDate() {
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(new Timestamp(calendar.getTime().getTime()));

        calendar.add(Calendar.MINUTE, VerificationToken.EXPIRATION);

        return new Date(calendar.getTime().getTime());
    }
}
