package com.aggregator.auth.usecases.dto;

import com.aggregator.auth.domain.valueobjects.UserRole;

public record LoginResponseDTO(
        String accessToken, String refreshToken, UserRole role, String tokenType) {}
