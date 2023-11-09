package com.shopster.databaseprovider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPrivateDataService {
    private final UserPrivateDataRepository userPrivateDataRepository;

    public void addData(final UserPrivateData data) {
        userPrivateDataRepository.save(data);
    }

    public Boolean updateData(final UserPrivateData data) {
        if(userPrivateDataRepository.findById(data.getUserId()).isPresent()) {
            userPrivateDataRepository.save(data);

            return true;
        }

        return false;
    }
}