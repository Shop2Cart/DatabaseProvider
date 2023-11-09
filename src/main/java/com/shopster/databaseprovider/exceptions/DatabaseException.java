package com.shopster.databaseprovider.exceptions;

import lombok.Getter;

import java.net.HttpURLConnection;

@Getter
public class DatabaseException extends Exception {
    private final Integer status;

    public DatabaseException(final String message, final Integer status) {
        super(message);
        this.status = status;
    }

    public DatabaseException(final Exception e, final Integer status) {
        super(e);
        this.status = status;
    }

    public DatabaseException(final Exception e) {
        this(e, HttpURLConnection.HTTP_NOT_ACCEPTABLE);
    }
}
