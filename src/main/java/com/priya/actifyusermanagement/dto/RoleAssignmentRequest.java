package com.priya.actifyusermanagement.dto;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;

public class RoleAssignmentRequest {

    @NotEmpty(message = "At least one role is required")
    private List<Long> roleIds;

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}

