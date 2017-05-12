package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wallance on 2/15/17.
 */
@Entity
@Table(name = "login_log", schema = "zx120_test")
public class LoginLogModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "account")
    private String account;

    @Column(name = "username")
    private String username;

    @Column(name = "host")
    private String host;

    @Column(name = "login_time")
    private Date loginTime;

    @Column(name = "message")
    private String message;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date logginTime) {
        this.loginTime = logginTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginLogModel that = (LoginLogModel) o;

        if (id != that.id) return false;
        if (!account.equals(that.account)) return false;
        if (!username.equals(that.username)) return false;
        if (!host.equals(that.host)) return false;
        if (!loginTime.equals(that.loginTime)) return false;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + account.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + host.hashCode();
        result = 31 * result + loginTime.hashCode();
        result = 31 * result + message.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "LoginLogModel{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", host='" + host + '\'' +
                ", loginTime=" + loginTime +
                ", message='" + message + '\'' +
                '}';
    }

}
