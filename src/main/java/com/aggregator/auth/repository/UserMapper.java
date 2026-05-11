package com.aggregator.auth.repository;

import com.aggregator.auth.domain.User;
import com.aggregator.auth.domain.valueobjects.Email;
import com.aggregator.auth.domain.valueobjects.PasswordHash;
import com.aggregator.shared.repository.Mapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<User, UserJpaEntity> {

    @Override
    public UserJpaEntity toEntity(User domain) {
        if (domain == null) {
            return null;
        }
        UserJpaEntity entity = new UserJpaEntity();
        entity.setId(domain.getId());
        entity.setEmail(domain.getEmail().getValue());
        entity.setPasswordHash(domain.getPasswordHash().getValue());
        entity.setDivisionId(domain.getDivisionId());
        entity.setRole(domain.getRole());
        entity.setCreatedAt(domain.getCreatedAt());
        entity.setUpdatedAt(domain.getUpdatedAt());
        return entity;
    }

    @Override
    public User toDomain(UserJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new User(
                entity.getId(),
                new Email(entity.getEmail()),
                new PasswordHash(entity.getPasswordHash()),
                entity.getDivisionId(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }
}
