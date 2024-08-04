package magenga.Hachimanage.common.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProjectUserRoleId implements Serializable {

    private Integer projectId;
    private Integer userId;

    public ProjectUserRoleId() {
    }

    public ProjectUserRoleId(Integer projectId, Integer userId) {
        this.projectId = projectId;
        this.userId = userId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectUserRoleId that = (ProjectUserRoleId) o;
        return Objects.equals(projectId, that.projectId) &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, userId);
    }
}
