package magenga.TimeLedger.common.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project_user")
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

    public ProjectUserRole() {
    }

    public ProjectUserRole(int projectId, int userId, int roleId) {
        this.projectId = projectId;
        this.userId = userId;
        this.roleId = roleId;
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

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
