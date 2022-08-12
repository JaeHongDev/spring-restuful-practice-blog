package com.example.sample.service.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepo extends JpaRepository<UserEntity,Long> {
}
