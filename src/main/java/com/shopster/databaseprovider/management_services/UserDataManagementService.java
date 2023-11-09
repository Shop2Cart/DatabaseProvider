package com.shopster.databaseprovider.management_services;

import com.shopster.databaseprovider.exceptions.DatabaseException;
import com.shopster.databaseprovider.models.UserPrivateData;
import com.shopster.databaseprovider.models.UserPublicData;
import com.shopster.databaseprovider.services.UserPrivateDataService;
import com.shopster.databaseprovider.services.UserPublicDataService;
import lombok.AllArgsConstructor;
import org.common.models.requests.RegistrationRequest;
import org.common.models.responses.SuccessResponse;
import org.common.models.types.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDataManagerService {
    private UserPrivateDataService privateDataService;
    private UserPublicDataService publicDataService;

    @Transactional(rollbackFor = DatabaseException.class)
    public Response addUser(final RegistrationRequest request) {
        final UserPrivateData privateData = new UserPrivateData(
                request.getUserId(), request.getPassword());
        final UserPublicData publicData = new UserPublicData(
                request.getUserId(), request.getUserFirstName(),
                request.getUserLastName(), Boolean.FALSE
        );

        privateDataService.addData(privateData);
        publicDataService.addData(publicData);

        return new SuccessResponse(String
                .format("User %s added successfully", request.getUserId()));
    }

}
