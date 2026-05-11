package com.aggregator.shared.repository.connections;

import com.aggregator.shared.repository.credentials.RepositoryCredentials;

public interface RepositoryConnectionFactory {
    RepositoryConnection createConnection(RepositoryCredentials credentials);
}
