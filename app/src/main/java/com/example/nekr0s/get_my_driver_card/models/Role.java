package com.example.nekr0s.get_my_driver_card.models;

import java.io.Serializable;

public class Role implements Serializable {
    private int roleId;
    private String roleName;

    public Role() {
        // keep empty
    }

    public Role(int roleId, String role) {
        this.roleId = roleId;
        this.roleName = role;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
