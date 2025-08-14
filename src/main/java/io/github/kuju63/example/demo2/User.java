package io.github.kuju63.example.demo2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
    @Column(name = "deleted_at")
    private OffsetDateTime deletedAt;
    @Column(name = "last_login")
    private OffsetDateTime lastLogin;
    @PersistenceContext
    @Column(name = "version", nullable = false)
    private Long version;

    @ManyToMany(targetEntity = UserGroup.class)
    private Set<UserGroup> userGroups;
}