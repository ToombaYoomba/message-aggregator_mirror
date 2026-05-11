package com.aggregator.auth.domain.exceptions;

import com.aggregator.shared.domain.exceptions.DomainException;

public class UserNotFoundException extends DomainException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
