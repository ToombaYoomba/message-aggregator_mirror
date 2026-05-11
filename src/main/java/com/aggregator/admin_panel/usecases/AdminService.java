package com.aggregator.admin_panel.usecases;

import com.aggregator.admin_panel.usecases.dto.CreateInviteRequestDTO;
import com.aggregator.admin_panel.usecases.dto.CreateInviteResponseDTO;
import com.aggregator.admin_panel.usecases.events.AdminCreatedInviteEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private final ApplicationEventPublisher eventPublisher;

    public AdminService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public CreateInviteResponseDTO createRegisterRequest(CreateInviteRequestDTO request) {
        eventPublisher.publishEvent(
                new AdminCreatedInviteEvent(request.email(), request.role(), request.divisionId()));

        return new CreateInviteResponseDTO(
                "Invite sent successfully to " + request.email().getValue());
    }
}
