package io.github.kuju63.example.demo2;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserGroupRepository extends JpaRepository<UserGroup, UUID> {
}