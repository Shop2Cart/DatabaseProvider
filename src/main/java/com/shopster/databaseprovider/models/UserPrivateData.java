package com.shopster.databaseprovider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "user_private_data")
public class UserPrivateData {
    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "password")
    private String password;
}

