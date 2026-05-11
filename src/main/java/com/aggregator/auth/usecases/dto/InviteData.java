package com.aggregator.auth.usecases.dto;

import com.aggregator.auth.domain.valueobjects.Email;
import com.aggregator.auth.domain.valueobjects.UserRole;
import java.util.UUID;

public record InviteData(Email email, UserRole role, UUID divisionId) {}
