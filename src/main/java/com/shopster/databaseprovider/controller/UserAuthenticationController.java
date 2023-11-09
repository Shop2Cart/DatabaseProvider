package com.shopster.databaseprovider;

import com.shopster.databaseprovider.models.UserPrivateData;
import com.shopster.databaseprovider.models.UserPublicData;
import com.shopster.databaseprovider.services.UserPrivateDataService;
import com.shopster.databaseprovider.services.UserPublicDataService;
import lombok.AllArgsConstructor;
import org.common.models.requests.LoginRequest;
import org.common.models.requests.RegistrationRequest;
import org.common.models.requests.ResetPasswordRequest;
import org.common.models.requests.UpdateUserAuthDataRequest;
import org.common.models.responses.SuccessResponse;
import org.common.models.types.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/userAuthService")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserAuthenticationController {
    private UserPrivateDataService userPrivateDataService;
    private UserPublicDataService userPublicDataService;

    @PostMapping(value = "/addUser")
    public Response addUser(@RequestBody final RegistrationRequest request) {
        userPrivateDataService
                .addData(new UserPrivateData(request.getUserId(), request.getPassword()));

        userPublicDataService
                .addData(new UserPublicData(
                        request.getUserId(),
                        request.getUserFirstName(),
                        request.getUserLastName(),
                        Boolean.FALSE
                ));

        return new SuccessResponse(String
                .format("Successfully Added Data For %s", request.getUserId()));
    }

    @PostMapping(value = "/updateUser")
    public Response updateUser(@RequestBody final UpdateUserAuthDataRequest request) {
        userPrivateDataService.updateData(userPrivateData);
    }

    @PostMapping(value = "/resetPassword")
    public Response resetPassword(@RequestBody final ResetPasswordRequest request) {
        userPrivateDataService.updateData(userPrivateData);
    }

    @GetMapping(value = "/signInVerify")
    public Response signInVerify(@RequestBody final LoginRequest request) {
        userPrivateDataService.updateData(userPrivateData);
    }
}
