package com.app.api.configuration;

/**
 * Created by Ashutosh Sharma
 */
public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}
