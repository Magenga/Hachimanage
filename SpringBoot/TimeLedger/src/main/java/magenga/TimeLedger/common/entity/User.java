package magenga.TimeLedger.common.entity;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_seq")
    private int userSeq;

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account")
    private String account;

    @Column(name = "password")
    private String password;

    public User(){}

    public User( String account, String password) {
        this.account = account;
        this.password = password;
    }

    public int getUserSeq() {
        return userSeq;
    }

    public void setUserSeq(int userSeq) {
        this.userSeq = userSeq;
    }

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

    @Override
    public String toString() {
        return "User{" +
                "userSeq=" + userSeq +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}