package com.aggregator.auth.repository;

import com.aggregator.auth.domain.User;
import com.aggregator.auth.domain.valueobjects.Email;
import com.aggregator.auth.usecases.interfaces.UserRepository;
import com.aggregator.shared.domain.query.PageQuery;
import com.aggregator.shared.domain.query.PageResult;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryHibernate implements UserRepository {
    @PersistenceContext private EntityManager entityManager;

    @Override
    public Optional<User> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return Optional.empty();
    }

    @Override
    public PageResult<User> findAll(PageQuery query) {
        return null;
    }

    @Override
    public void save(User user) {
    }

    @Override
    public void deleteById(UUID id) {}
}
