package com.aggregator.shared.repository.credentials;

public record PostgresCredentials(
        String host, int port, String database, String username, String password)
        implements RepositoryCredentials {}
