package com.aggregator.notifications.usecases;

import com.aggregator.auth.domain.valueobjects.Email;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    public void sendMissedPasswordEmail(Email email) {}

    public void sendInviteEmail(Email email, String inviteToken) {}
}
