package com.shopster.databaseprovider.models;

import lombok.Getter;
import lombok.Setter;
import org.common.models.types.Response;

@Getter
@Setter
public class UserPublicDataGetResponse extends Response {
    private UserPublicData publicData;

    public UserPublicDataGetResponse(final Integer status,
                                     final String message,
                                     final UserPublicData publicData) {
        super(status, message);
        this.publicData = publicData;
    }
}
