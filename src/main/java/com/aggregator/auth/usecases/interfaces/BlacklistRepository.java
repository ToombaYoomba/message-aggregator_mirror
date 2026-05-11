package com.aggregator.auth.usecases.interfaces;

import java.time.Duration;

public interface BlacklistRepository {
    void add(String token, Duration ttl);

    boolean isBlacklisted(String token);
}
