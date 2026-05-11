package com.aggregator.admin_panel.usecases.events;

import com.aggregator.auth.domain.valueobjects.Email;
import com.aggregator.auth.domain.valueobjects.UserRole;
import java.util.UUID;

public record AdminCreatedInviteEvent(Email email, UserRole role, UUID divisionId) {}
