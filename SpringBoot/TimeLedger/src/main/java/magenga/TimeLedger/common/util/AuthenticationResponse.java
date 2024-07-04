package magenga.TimeLedger.common.util;

public class AuthenticationResponse {
    private String token;
    private int userId;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, int userId) {
        this.token = token;
        this.userId = userId;
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

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "token='" + token + '\'' +
                ", userId=" + userId +
                '}';
    }
}