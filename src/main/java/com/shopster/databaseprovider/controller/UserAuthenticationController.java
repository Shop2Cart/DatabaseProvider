package com.shopster.databaseprovider.controller;

import com.shopster.databaseprovider.exceptions.DatabaseException;
import com.shopster.databaseprovider.management_services.UserDataManagementService;
import lombok.AllArgsConstructor;
import org.common.models.requests.GetUserDataRequest;
import org.common.models.requests.LoginRequest;
import org.common.models.requests.RegistrationRequest;
import org.common.models.requests.ResetPasswordRequest;
import org.common.models.requests.UpdateUserAuthDataRequest;
import org.common.models.responses.FailedResponse;
import org.common.models.types.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/userAuthService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAuthenticationController {
    private UserDataManagementService userDataManagementService;

    @PostMapping(value = "/addUser")
    public Response addUser(@RequestBody final RegistrationRequest request) {
        try {
            return userDataManagementService.addUser(request);
        } catch (final DatabaseException e) {
            final String message = String.format("Failed To Add User %s", request.getUserId());
            final FailedResponse response = new FailedResponse(message, e.toString());

            response.setStatus(e.getStatus());
            return response;
        }
    }

    @PostMapping(value = "/updateUser")
    public Response updateUser(@RequestBody final UpdateUserAuthDataRequest request) {
        try {
            return userDataManagementService.updateUser(request);
        } catch (final DatabaseException e) {
            final String message = String.format("Failed To Update User %s", request.getUserId());
            final FailedResponse response = new FailedResponse(message, e.toString());

            response.setStatus(e.getStatus());
            return response;
        }
    }

    @PostMapping(value = "/resetPassword")
    public Response resetPassword(@RequestBody final ResetPasswordRequest request) {
        try {
            return userDataManagementService.updatePassword(request);
        } catch (final DatabaseException e) {
            final String message = String.format("Failed To Reset User %s Password", request.getUserId());
            final FailedResponse response = new FailedResponse(message, e.toString());

            response.setStatus(e.getStatus());
            return response;
        }
    }

    @GetMapping(value = "/signInVerify")
    public Response signInVerify(@RequestBody final LoginRequest request) {
        try {
            return userDataManagementService.signIn(request);
        } catch (final DatabaseException e) {
            final String message = String.format("Sign In Failed for %s", request.getUserId());
            final FailedResponse response = new FailedResponse(message, e.toString());

            response.setStatus(e.getStatus());
            return response;
        }
    }

    @GetMapping(value = "/getUser")
    public Response getUser(@RequestBody final GetUserDataRequest request) {
        try {
            return userDataManagementService.getUser(request);
        } catch (final DatabaseException e) {
            final String message = String.format("Unable To Get The User %s", request.getUserId());
            final FailedResponse response = new FailedResponse(message, e.toString());

            response.setStatus(e.getStatus());
            return response;
        }
    }
}
