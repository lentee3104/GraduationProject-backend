package org.example.graduationprojectbackend.service;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

import org.example.graduationprojectbackend.entity.User;
import org.example.graduationprojectbackend.entity.Role;
import org.example.graduationprojectbackend.serviceImpl.UserDetailsImpl;

class UserDetailsImplTest {

    // 测试数据准备
    private final User mockUser = new User(
            1L,
            "testuser",
            "test@example.com",
            "$2a$10$fakePasswordHash",
            true,
            List.of(new Role("ROLE_ADMIN")) // 注意角色前缀
    );

    @Test
    void build_ShouldCorrectlyMapUserDetails() {
        // When
        UserDetailsImpl userDetails = UserDetailsImpl.build(mockUser);

        // Then
        assertAll(
                () -> assertEquals(1L, userDetails.getId()),
                () -> assertEquals("testuser", userDetails.getUsername()),
                () -> assertEquals("test@example.com", userDetails.getEmail()),
                () -> assertEquals("$2a$10$fakePasswordHash", userDetails.getPassword()),
                () -> assertTrue(containsAuthority(userDetails, "ROLE_ADMIN")),
                () -> assertTrue(userDetails.isEnabled())
        );
    }

    @Test
    void getAuthorities_ShouldHandleEmptyRoles() {
        // Given
        User userWithNoRoles = new User(
                2L,
                "emptyrole",
                "empty@test.com",
                "hash",
                true,
                Collections.emptyList()
        );

        // When
        UserDetailsImpl userDetails = UserDetailsImpl.build(userWithNoRoles);

        // Then
        assertTrue(userDetails.getAuthorities().isEmpty());
    }

    @Test
    void equals_ShouldWorkBasedOnId() {
        // Given
        UserDetailsImpl user1 = UserDetailsImpl.build(mockUser);
        UserDetailsImpl user2 = UserDetailsImpl.build(mockUser);
        UserDetailsImpl differentUser = UserDetailsImpl.build(
                new User(99L, "other", "other@test.com", "pwd", true, List.of())
        );

        // Then
        assertAll(
                () -> assertEquals(user1, user2),
                () -> assertNotEquals(user1, differentUser),
                () -> assertNotEquals(user1, null),
                () -> assertNotEquals(user1, new Object())
        );
    }

    @Test
    void accountStatusMethods_ShouldReturnTrueByDefault() {
        // Given
        UserDetailsImpl userDetails = UserDetailsImpl.build(mockUser);

        // Then
        assertAll(
                () -> assertTrue(userDetails.isAccountNonExpired()),
                () -> assertTrue(userDetails.isAccountNonLocked()),
                () -> assertTrue(userDetails.isCredentialsNonExpired())
        );
    }

    @Test
    void hashCode_ShouldConsistentWithEquals() {
        // Given
        UserDetailsImpl user1 = UserDetailsImpl.build(mockUser);
        UserDetailsImpl user2 = UserDetailsImpl.build(mockUser);

        // Then
        assertEquals(user1.hashCode(), user2.hashCode());
    }

    // Helper method
    private boolean containsAuthority(UserDetailsImpl userDetails, String authority) {
        return userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(authority));
    }

//    // Mock User and Role classes (可单独定义或使用项目实体类)
//    static class User {
//        private Long id;
//        private String username;
//        private String email;
//        private String password;
//        private boolean enabled;
//        private List<Role> roles;
//
//        public User(Long id, String username, String email, String password,
//                    boolean enabled, List<Role> roles) {
//            this.id = id;
//            this.username = username;
//            this.email = email;
//            this.password = password;
//            this.enabled = enabled;
//            this.roles = roles;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public Long getId() {
//            return id;
//        }
//
//        public String getEmail() {
//            return email;
//        }
//
//        public String getPassword() {
//            return password;
//        }
//
//        public boolean isEnabled() {
//            return enabled;
//        }
//
//        public List<Role> getRoles() {
//            return roles;
//        }
//
//        // Getters 省略...
//    }
//
//    static class Role {
//        private String name;
//
//        public Role(String name) {
//            this.name = name;
//        }
//
//        public String getName() {
//            return name;
//        }
//    }
//}