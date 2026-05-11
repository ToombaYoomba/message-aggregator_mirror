package com.aggregator.auth.domain.exceptions;

import com.aggregator.shared.domain.exceptions.DomainException;

public class UserAlreadyExistsException extends DomainException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
