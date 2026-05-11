package com.aggregator.auth.usecases.dto;

import com.aggregator.auth.domain.valueobjects.Email;

public record MissedPasswordRequestDTO(Email email) {}
