package com.shopster.databaseprovider;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPrivateDataRepository extends JpaRepository<UserPrivateData, String> {
}