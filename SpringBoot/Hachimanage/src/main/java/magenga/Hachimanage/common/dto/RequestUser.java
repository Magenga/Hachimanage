package magenga.Hachimanage.common.dto;

public class RequestUser {
    private String account;
    private String password;
    private String email;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() { return email;}

    public void setEmail(String email) {this.email = email;}
}
