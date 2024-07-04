package magenga.TimeLedger.common.entity;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
@Entity
@Table(name = "users")
public class User {

//    PK,id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

//    login account
    @Column(name = "account")
    private String account;

//    login password
//    @JsonIgnore//JSON序列化時忽略(完成開發後使用)
    @Column(name = "password")
    private String password;

//    information email
    @Column(name = "email")
    private String email;

//    just for record user created
    @Column(name = "created_at")
    private Date createdTime;

    public User(){}

    public User( String account, String password, String email) {
        this.account = account;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "userSeq=" + userSeq +
//                ", account='" + account + '\'' +
//                ", password='" + password + '\'' +
//                '}';
//    }

}
