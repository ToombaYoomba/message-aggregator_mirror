package com.aggregator.shared.repository.connections;

import com.aggregator.shared.repository.credentials.RepositoryCredentials;

// TODO: добавить отлов кастомных ошибок для метода connect
public interface RepositoryConnection {
    void connect(RepositoryCredentials credentials);
}
