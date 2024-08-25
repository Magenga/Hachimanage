package magenga.Hachimanage.common.dto;

public class InviteUserRequest {

    private int projectId;
    private String userAccount;

    public InviteUserRequest(int projectId, String userAccount) {
        this.projectId = projectId;
        this.userAccount = userAccount;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "InviteUserRequest{" +
                "projectId=" + projectId +
                ", userAccount='" + userAccount + '\'' +
                '}';
    }
}
