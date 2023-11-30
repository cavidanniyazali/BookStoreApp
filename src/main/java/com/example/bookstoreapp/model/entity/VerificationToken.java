package com.example.bookstoreapp.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "verification_token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String token;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "expired_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_authentication_id", nullable = false)
    private UserAuthentication userAuthentication;

    public VerificationToken(UserAuthentication userAuthentication) {
        this.userAuthentication = userAuthentication;
        createdDate = new Date();
        expiredDate = new Date(createdDate.getTime() + (24 * 60 * 60 * 1000));
        token = UUID.randomUUID().toString();
    }
}
