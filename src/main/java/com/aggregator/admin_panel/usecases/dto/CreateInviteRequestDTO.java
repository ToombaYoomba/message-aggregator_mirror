package com.aggregator.admin_panel.usecases.dto;

import com.aggregator.auth.domain.valueobjects.Email;
import com.aggregator.auth.domain.valueobjects.UserRole;
import java.util.UUID;

public record CreateInviteRequestDTO(Email email, UserRole role, UUID divisionId) {}
