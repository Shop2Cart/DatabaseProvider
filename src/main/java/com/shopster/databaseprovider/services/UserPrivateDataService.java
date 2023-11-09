package com.shopster.databaseprovider.services;

import com.shopster.databaseprovider.exceptions.DatabaseException;
import com.shopster.databaseprovider.models.UserPrivateData;
import com.shopster.databaseprovider.repositories.UserPrivateDataRepository;
import lombok.RequiredArgsConstructor;;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
@RequiredArgsConstructor
public class UserPrivateDataService {
    private final UserPrivateDataRepository userPrivateDataRepository;

    public void addData(final UserPrivateData data) throws DatabaseException {
        try {
            if (userPrivateDataRepository.existsById(data.getUserId())) {
                throw new DatabaseException("Id already exists",
                        HttpURLConnection.HTTP_CONFLICT);
            }

            userPrivateDataRepository.save(data);
        } catch (final IllegalArgumentException e) {
            throw new DatabaseException(e, HttpURLConnection.HTTP_BAD_REQUEST);
        } catch (final OptimisticEntityLockException e) {
            throw new DatabaseException(e, HttpURLConnection.HTTP_CONFLICT);
        }
    }

    public void updateData(final UserPrivateData data) throws DatabaseException {
        final UserPrivateData oldPublicData = getData(data.getUserId());

        if (oldPublicData.equals(data)) {
            throw new DatabaseException("No change in the data",
                    HttpURLConnection.HTTP_NOT_MODIFIED);
        }

        userPrivateDataRepository.save(data);
    }

    public UserPrivateData getData(final String userId) throws DatabaseException {
        if (!userPrivateDataRepository.existsById(userId)) {
            throw new DatabaseException("User doesn't exists",
                    HttpURLConnection.HTTP_NOT_FOUND);
        }

        return userPrivateDataRepository.getReferenceById(userId);
    }
}