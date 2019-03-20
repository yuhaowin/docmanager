package com.chzu.realm;

public class Role {
    private Integer roleId;

    private String roleName;

    private String permissions;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleid) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions == null ? null : permissions.trim();
    }
}