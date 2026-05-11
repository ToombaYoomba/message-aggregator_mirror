package com.aggregator.auth.domain;

import com.aggregator.auth.domain.valueobjects.Email;
import com.aggregator.auth.domain.valueobjects.PasswordHash;
import com.aggregator.auth.domain.valueobjects.UserRole;
import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private UUID id;
    private Email email;
    private PasswordHash passwordHash;
    private UUID divisionId;
    private UserRole role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private User() {}

    public User(
            UUID id,
            Email email,
            PasswordHash passwordHash,
            UUID divisionId,
            UserRole role,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.divisionId = divisionId;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User createNew(
            Email email, PasswordHash passwordHash, UUID divisionId, LocalDateTime now) {
        return new User(
                UUID.randomUUID(), email, passwordHash, divisionId, UserRole.USER, now, now);
    }

    public void changeRole(UserRole newRole, LocalDateTime now) {
        if (newRole == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
        this.role = newRole;
        this.updatedAt = now;
    }

    public void updatePassword(PasswordHash newPasswordHash, LocalDateTime now) {
        if (newPasswordHash == null) {
            throw new IllegalArgumentException("Password hash cannot be null");
        }
        this.passwordHash = newPasswordHash;
        this.updatedAt = now;
    }

    public void changeDivision(UUID newDivisionId, LocalDateTime now) {
        this.divisionId = newDivisionId;
        this.updatedAt = now;
    }

    public UUID getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }

    public PasswordHash getPasswordHash() {
        return passwordHash;
    }

    public UUID getDivisionId() {
        return divisionId;
    }

    public UserRole getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
