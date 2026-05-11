package com.aggregator.auth.usecases.interfaces;

import com.aggregator.auth.domain.User;
import com.aggregator.auth.domain.valueobjects.Email;
import com.aggregator.shared.domain.query.PageQuery;
import com.aggregator.shared.domain.query.PageResult;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(UUID id);

    Optional<User> findByEmail(Email email);

    PageResult<User> findAll(PageQuery query);

    void save(User user);

    void deleteById(UUID id);
}
