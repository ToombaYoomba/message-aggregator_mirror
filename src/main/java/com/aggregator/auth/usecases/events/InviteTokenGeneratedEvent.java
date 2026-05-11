package com.aggregator.auth.usecases.events;

import com.aggregator.auth.domain.valueobjects.Email;

public record InviteTokenGeneratedEvent(Email email, String inviteToken) {}
