package io.github.kuju63.example.demo2;

import org.springframework.data.jpa.domain.Specification;

/**
 * Specification class for filtering User entities based on various criteria.
 */
public class UserSpecification {
    public  static Specification<User> byEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null || email.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }
            return criteriaBuilder.like(root.get("email"), email + "%");
        };
    }

    public static Specification<User> byEnabled(boolean enabled) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("enabled"), enabled);
    }

    public static Specification<User> byName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }
            return criteriaBuilder.like(root.get("name"), name + "%");
        };
    }

    public static Specification<User> byUserGroupId(String userGroupId) {
        return (root, query, criteriaBuilder) -> {
            if (userGroupId == null || userGroupId.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }
            return criteriaBuilder.equal(root.join("userGroups").get("id"), userGroupId);
        };
    }

    public static Specification<User> byEnabledGroup(boolean enabledGroup) {
        return (root, query, criteriaBuilder) -> {
            if (enabledGroup) {
                return criteriaBuilder.isTrue(root.join("userGroups").get("enabled"));
            } else {
                return criteriaBuilder.isFalse(root.join("userGroups").get("enabled"));
            }
        };
    }
}
