package com.aggregator.auth.usecases.events;

import com.aggregator.admin_panel.usecases.events.AdminCreatedInviteEvent;
import com.aggregator.auth.usecases.interfaces.InviteRepository;
import java.time.Duration;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class InviteEventListener {

    private final InviteRepository inviteRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final int ttlHours;

    public InviteEventListener(
            InviteRepository inviteRepository,
            ApplicationEventPublisher eventPublisher,
            @Value("${APP_INVITE_TTL_HOURS:24}") int ttlHours) {
        this.inviteRepository = inviteRepository;
        this.eventPublisher = eventPublisher;
        this.ttlHours = ttlHours;
    }

    @ApplicationModuleListener
    public void onAdminCreatedInvite(AdminCreatedInviteEvent event) {
        String inviteToken = UUID.randomUUID().toString();
        Duration ttl = Duration.ofHours(ttlHours);

        inviteRepository.save(inviteToken, event.email(), event.role(), ttl);

        eventPublisher.publishEvent(new InviteTokenGeneratedEvent(event.email(), inviteToken));
    }
}
