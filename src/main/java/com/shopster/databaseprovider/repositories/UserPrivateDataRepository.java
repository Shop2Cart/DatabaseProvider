package com.shopster.databaseprovider.repositories;

import com.shopster.databaseprovider.models.UserPrivateData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrivateDataRepository extends JpaRepository<UserPrivateData, String> {
}