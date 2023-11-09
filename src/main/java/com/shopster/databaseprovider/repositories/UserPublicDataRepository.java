package com.shopster.databaseprovider.repositories;

import com.shopster.databaseprovider.models.UserPublicData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPublicDataRepository extends JpaRepository<UserPublicData, String> {
}
