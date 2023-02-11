package com.api.djobs.oauth.exception;

public class OAuthProviderMissMatchException extends RuntimeException {

    public OAuthProviderMissMatchException(String message) {
        super(message);
    }
}