package com.shopster.databaseprovider.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "public_data")
@Proxy(lazy = false)
public class UserPublicData {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "is_verified")
    private Boolean isVerified;
}
