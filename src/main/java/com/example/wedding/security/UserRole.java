package com.example.wedding.security;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.wedding.security.UserPermission.*;

public enum UserRole {
    USER(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(USER_READ, USER_WRITE));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }


}
