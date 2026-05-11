package com.aggregator.auth.usecases;

import com.aggregator.auth.domain.User;
import com.aggregator.auth.domain.exceptions.AuthException;
import com.aggregator.auth.domain.exceptions.UserAlreadyExistsException;
import com.aggregator.auth.domain.valueobjects.Email;
import com.aggregator.auth.domain.valueobjects.PasswordHash;
import com.aggregator.auth.usecases.dto.InviteData;
import com.aggregator.auth.usecases.dto.LoginRequestDTO;
import com.aggregator.auth.usecases.dto.LoginResponseDTO;
import com.aggregator.auth.usecases.dto.LogoutRequestDTO;
import com.aggregator.auth.usecases.dto.LogoutResponseDTO;
import com.aggregator.auth.usecases.dto.MissedPasswordRequestDTO;
import com.aggregator.auth.usecases.dto.MissedPasswordResponseDTO;
import com.aggregator.auth.usecases.dto.RegisterRequestDTO;
import com.aggregator.auth.usecases.dto.RegisterResponseDTO;
import com.aggregator.auth.usecases.events.PasswordResetRequestedEvent;
import com.aggregator.auth.usecases.interfaces.BlacklistRepository;
import com.aggregator.auth.usecases.interfaces.InviteRepository;
import com.aggregator.auth.usecases.interfaces.PasswordEncoder;
import com.aggregator.auth.usecases.interfaces.TokenProvider;
import com.aggregator.auth.usecases.interfaces.UserRepository;
import com.aggregator.shared.domain.TimeProvider;
import java.time.Duration;
import java.util.Map;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    private final BlacklistRepository blacklistRepository;
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final ApplicationEventPublisher eventPublisher;
    private final InviteRepository inviteRepository;
    private final TimeProvider timeProvider;

    public AuthService(
            BlacklistRepository blacklistRepository,
            UserRepository userRepository,
            TokenProvider tokenProvider,
            PasswordEncoder passwordEncoder,
            ApplicationEventPublisher eventPublisher,
            InviteRepository inviteRepository,
            TimeProvider timeProvider) {
        this.userRepository = userRepository;
        this.blacklistRepository = blacklistRepository;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.eventPublisher = eventPublisher;
        this.inviteRepository = inviteRepository;
        this.timeProvider = timeProvider;
    }

    @Transactional
    public LoginResponseDTO authenticate(LoginRequestDTO request) throws AuthException {
        Email email = request.email();
        String password = request.password();
        User foundUser =
                userRepository
                        .findByEmail(email)
                        .orElseThrow(() -> new AuthException("Invalid email or password"));

        if (!passwordEncoder.matches(password, foundUser.getPasswordHash().getValue())) {
            throw new AuthException("Invalid email or password");
        }

        Map<String, Object> accessPayload =
                Map.of(
                        "sub", foundUser.getEmail().getValue(),
                        "id", foundUser.getId(),
                        "role", foundUser.getRole());
        String accessToken = tokenProvider.generateAccessToken(accessPayload);

        Map<String, Object> refreshPayload =
                Map.of(
                        "sub", foundUser.getEmail().getValue(),
                        "id", foundUser.getId());
        String refreshToken = tokenProvider.generateRefreshToken(refreshPayload);

        return new LoginResponseDTO(accessToken, refreshToken, foundUser.getRole(), "Bearer");
    }

    @Transactional
    public RegisterResponseDTO register(RegisterRequestDTO request) throws AuthException {
        InviteData inviteData =
                inviteRepository
                        .findByToken(request.inviteToken())
                        .orElseThrow(() -> new AuthException("Invalid or expired invite token"));

        if (userRepository.findByEmail(inviteData.email()).isPresent()) {
            throw new UserAlreadyExistsException("User with this email already exists");
        }

        PasswordHash hashedPassword = new PasswordHash(passwordEncoder.encode(request.password()));

        User newUser =
                User.createNew(
                        inviteData.email(),
                        hashedPassword,
                        inviteData.divisionId(),
                        timeProvider.now());

        newUser.changeRole(inviteData.role(), timeProvider.now());

        userRepository.save(newUser);

        inviteRepository.delete(request.inviteToken());

        return new RegisterResponseDTO("Registration successful");
    }

    public MissedPasswordResponseDTO missedPassword(MissedPasswordRequestDTO request)
            throws AuthException {
        Email email = request.email();
        eventPublisher.publishEvent(new PasswordResetRequestedEvent(email));

        return new MissedPasswordResponseDTO();
    }

    public LogoutResponseDTO logout(LogoutRequestDTO request) throws AuthException {
        String accessToken = request.accessToken();
        String refreshToken = request.refreshToken();

        if (accessToken == null || refreshToken == null) {
            throw new AuthException("Missing access token or refresh token");
        }

        if (tokenProvider.isValid(accessToken)) {
            Duration accessDuration = tokenProvider.getRemainingTime(accessToken);
            if (!accessDuration.isNegative()) {
                blacklistRepository.add(accessToken, accessDuration);
            }
        }

        if (tokenProvider.isValid(refreshToken)) {
            Duration refreshDuration = tokenProvider.getRemainingTime(refreshToken);
            if (!refreshDuration.isNegative()) {
                blacklistRepository.add(refreshToken, refreshDuration);
            }
        }

        return new LogoutResponseDTO();
    }
}
