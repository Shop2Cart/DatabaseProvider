package com.shopster.databaseprovider.controller;

import com.shopster.databaseprovider.exceptions.DatabaseException;
import com.shopster.databaseprovider.management_services.UserDataManagementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.common.models.requests.GetUserDataRequest;
import org.common.models.requests.LoginRequest;
import org.common.models.requests.RegistrationRequest;
import org.common.models.requests.ResetPasswordRequest;
import org.common.models.requests.UpdateUserAuthDataRequest;
import org.common.models.responses.ErroredResponse;
import org.common.models.responses.FailedResponse;
import org.common.models.types.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/userAuthDatabaseService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAuthenticationController {
    private UserDataManagementService userDataManagementService;

    @PostMapping(value = "/addUser")
    public Response addUser(@RequestBody final RegistrationRequest request) throws DatabaseException {
        return userDataManagementService.addUser(request);
    }

    @PostMapping(value = "/updateUser")
    public Response updateUser(@RequestBody final UpdateUserAuthDataRequest request) throws DatabaseException {
        return userDataManagementService.updateUser(request);
    }

    @PostMapping(value = "/resetPassword")
    public Response resetPassword(@RequestBody final ResetPasswordRequest request) throws DatabaseException {
        return userDataManagementService.updatePassword(request);
    }

    @GetMapping(value = "/signInVerify")
    public Response signInVerify(@RequestBody final LoginRequest request) throws DatabaseException {
        return userDataManagementService.signIn(request);
    }

    @GetMapping(value = "/getUser")
    public Response getUser(@RequestBody final GetUserDataRequest request) throws DatabaseException {
        return userDataManagementService.getUser(request);
    }

    @ExceptionHandler(value = DatabaseException.class)
    public Response databaseExceptionHandler(final DatabaseException e) {
        return new FailedResponse(e.getStatus(),
                "Failed to complete the Request", e.toString());
    }

    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(final Exception e) {
        return new ErroredResponse("An Error Occurred", e);
    }
}
