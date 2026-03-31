package io.jayksss.api.auth.entity;

import io.jayksss.api.common.uuid.UuidV4;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "auth_role")
public class AuthRole {
    @Id
    @Column(name = "role_id", length = 36, nullable = false)
    private String roleId;

    @Column(name = "role_code", length = 50, nullable = false, unique = true)
    private String roleCode;

    @Column(name = "role_name", length = 100, nullable = false)
    private String roleName;

    @PrePersist
    @SuppressWarnings("unused")
    private void prePersist() {
        if (roleId == null || roleId.isBlank()) {
            roleId = UuidV4.newString();
        }
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

