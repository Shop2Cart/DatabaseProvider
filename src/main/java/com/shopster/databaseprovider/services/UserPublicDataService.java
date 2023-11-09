package com.shopster.databaseprovider.services;

import com.shopster.databaseprovider.exceptions.DatabaseException;
import com.shopster.databaseprovider.models.UserPublicData;
import com.shopster.databaseprovider.repositories.UserPublicDataRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.stereotype.Service;

import java.net.HttpURLConnection;

@Service
@RequiredArgsConstructor
public class UserPublicDataService {
    private final UserPublicDataRepository userPublicDataRepository;

    public void addData(final UserPublicData data) throws DatabaseException {
        try {
            if (userPublicDataRepository.existsById(data.getUserId())) {
                throw new DatabaseException("Id already exists",
                        HttpURLConnection.HTTP_CONFLICT);
            }

            userPublicDataRepository.save(data);
        } catch (final IllegalArgumentException e) {
            throw new DatabaseException(e, HttpURLConnection.HTTP_BAD_REQUEST);
        } catch (final OptimisticEntityLockException e) {
            throw new DatabaseException(e, HttpURLConnection.HTTP_CONFLICT);
        }
    }

    public void updateData(final UserPublicData data) throws DatabaseException {
        final UserPublicData oldPublicData = getData(data.getUserId());

        data.setIsVerified(oldPublicData.getIsVerified());

        if (oldPublicData.equals(data)) {
            throw new DatabaseException("No change in the data",
                    HttpURLConnection.HTTP_NOT_MODIFIED);
        }

        userPublicDataRepository.save(data);
    }

    public UserPublicData getData(final String userId) throws DatabaseException {
        if (!userPublicDataRepository.existsById(userId)) {
            throw new DatabaseException("User doesn't exists",
                    HttpURLConnection.HTTP_NOT_FOUND);
        }

        return userPublicDataRepository.getReferenceById(userId);
    }
}
