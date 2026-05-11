package com.aggregator.shared.repository.credentials;

public record RedisCredentials(String host, int port, String password, int database)
        implements RepositoryCredentials {}
