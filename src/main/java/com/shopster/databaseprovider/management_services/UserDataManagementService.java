package com.shopster.databaseprovider.management_services;

import com.shopster.databaseprovider.exceptions.DatabaseException;
import com.shopster.databaseprovider.models.UserPrivateData;
import com.shopster.databaseprovider.models.UserPublicData;
import com.shopster.databaseprovider.models.UserPublicDataGetResponse;
import com.shopster.databaseprovider.services.UserPrivateDataService;
import com.shopster.databaseprovider.services.UserPublicDataService;
import lombok.AllArgsConstructor;
import org.common.models.requests.GetUserDataRequest;
import org.common.models.requests.LoginRequest;
import org.common.models.requests.RegistrationRequest;
import org.common.models.requests.ResetPasswordRequest;
import org.common.models.requests.UpdateUserAuthDataRequest;
import org.common.models.responses.SuccessResponse;
import org.common.models.types.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.HttpURLConnection;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDataManagementService {
    private UserPrivateDataService privateDataService;
    private UserPublicDataService publicDataService;

    @Transactional(rollbackFor = DatabaseException.class)
    public Response addUser(final RegistrationRequest request) throws DatabaseException {
        final UserPrivateData privateData = new UserPrivateData(
                request.getUserId(), request.getPassword());
        final UserPublicData publicData = new UserPublicData(
                request.getUserId(), request.getUserFirstName(),
                request.getUserLastName(), Boolean.FALSE
        );

        privateDataService.addData(privateData);
        publicDataService.addData(publicData);

        final SuccessResponse response = new SuccessResponse(String
                .format("User %s added successfully", request.getUserId()));

        response.setStatus(HttpURLConnection.HTTP_CREATED);
        return response;
    }

    @Transactional(rollbackFor = DatabaseException.class)
    public Response updateUser(final UpdateUserAuthDataRequest request) throws DatabaseException {
        final UserPublicData newPublicData = new UserPublicData(
                request.getUserId(), request.getUserFirstName(),
                request.getUserLastName(), null
        );

        publicDataService.updateData(newPublicData);

        final SuccessResponse response = new SuccessResponse(String
                .format("Updated %s successfully", request.getUserId()));

        response.setStatus(HttpURLConnection.HTTP_ACCEPTED);
        return response;
    }

    @Transactional
    public Response getUser(final GetUserDataRequest request) throws DatabaseException {
        final UserPublicData publicData = publicDataService.getData(request.getUserId());

        return new UserPublicDataGetResponse(HttpURLConnection.HTTP_OK,
                String.format("Found %s Successfully", request.getUserId()), publicData);
    }

    @Transactional(rollbackFor = DatabaseException.class)
    public Response updatePassword(final ResetPasswordRequest request) throws DatabaseException {
        final UserPrivateData privateData =
                new UserPrivateData(request.getUserId(), request.getPassword());

        privateDataService.updateData(privateData);

        final SuccessResponse response = new SuccessResponse(String
                .format("Updated Password for %s Successfully", request.getUserId()));

        response.setStatus(HttpURLConnection.HTTP_ACCEPTED);
        return response;
    }

    @Transactional
    public Response signIn(final LoginRequest request) throws DatabaseException {
        final UserPrivateData privateData = privateDataService.getData(request.getUserId());

        if (!privateData.getPassword().equals(request.getPassword())) {
            throw new DatabaseException("Invalid Credentials", HttpURLConnection.HTTP_UNAUTHORIZED);
        }

        final SuccessResponse response = new SuccessResponse("Verified Successfully");

        response.setStatus(HttpURLConnection.HTTP_OK);
        return response;
    }
}
