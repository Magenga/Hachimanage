package magenga.Hachimanage.common.util;

public class AuthenticationResponse {
    private String token;
    private int userId;
    private String account;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, int userId, String account) {
        this.token = token;
        this.userId = userId;
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}