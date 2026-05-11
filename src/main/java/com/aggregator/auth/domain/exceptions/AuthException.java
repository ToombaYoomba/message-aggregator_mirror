package com.aggregator.auth.domain.exceptions;

import com.aggregator.shared.domain.exceptions.DomainException;

public class AuthException extends DomainException {
    public AuthException(String message) {
        super(message);
    }
}
