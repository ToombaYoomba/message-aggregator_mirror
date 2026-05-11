package com.aggregator.shared.repository.credentials;

public record KafkaCredentials(String bootstrapServers, String clientId, String securityProtocol)
        implements RepositoryCredentials {}
