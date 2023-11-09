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
@Table(name = "private_data")
@Proxy(lazy = false)
public class UserPrivateData {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;
}

