package com.aggregator.auth.usecases.interfaces;

import com.aggregator.auth.domain.valueobjects.Email;
import com.aggregator.auth.domain.valueobjects.UserRole;
import com.aggregator.auth.usecases.dto.InviteData;
import java.time.Duration;
import java.util.Optional;

public interface InviteRepository {
    void save(String token, Email email, UserRole role, Duration ttl);

    Optional<InviteData> findByToken(String token);

    void delete(String token);
}
