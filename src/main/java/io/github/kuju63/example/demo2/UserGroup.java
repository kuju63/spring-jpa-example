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
@Table(name = "user_group")
public class UserGroup {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "created_by", nullable = false)
    private OffsetDateTime createdBy;
    @Column(name = "updated_by", nullable = false)
    private OffsetDateTime updatedBy;
    @PersistenceContext
    @Column(name = "version", nullable = false)
    private Long version;

    @ManyToMany(mappedBy = "userGroups", targetEntity = User.class)
    private Set<User> users;
}