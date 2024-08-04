package magenga.Hachimanage.common.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"project_id", "user_id", "role_id"})
})
@IdClass(ProjectUserRoleId.class)
public class ProjectUserRole {
    @Id
    @Column(name = "project_id", nullable = false)
    private int projectId;

    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "role_id", nullable = false)
    private int roleId;

    public ProjectUserRole(int projectId, int userId, int roleId) {
        this.projectId = projectId;
        this.userId = userId;
        this.roleId = roleId;
    }

    public ProjectUserRole() {
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}