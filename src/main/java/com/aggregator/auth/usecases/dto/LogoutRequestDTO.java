package com.aggregator.auth.usecases.dto;

public record LogoutRequestDTO(String accessToken, String refreshToken) {}
