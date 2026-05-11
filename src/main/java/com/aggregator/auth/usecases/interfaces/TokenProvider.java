package com.aggregator.auth.usecases.interfaces;

import java.time.Duration;
import java.util.Map;

public interface TokenProvider {
    String generateAccessToken(Map<String, Object> payload);

    String generateRefreshToken(Map<String, Object> payload);

    String extractEmail(String token);

    boolean isValid(String token);

    Duration getRemainingTime(String refreshToken);
}
