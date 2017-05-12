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
 * Created by wallance on 2/22/17.
 */
@Entity
@Table(name = "err_log", schema = "zx120_test")
public class ErrLogModel implements Serializable {

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

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "exception")
    private String exception;

    @Column(name = "occur_time")
    private Date occurTime;

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

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ErrLogModel that = (ErrLogModel) o;

        if (id != that.id) return false;
        if (!account.equals(that.account)) return false;
        if (!username.equals(that.username)) return false;
        if (!host.equals(that.host)) return false;
        if (!methodName.equals(that.methodName)) return false;
        if (!exception.equals(that.exception)) return false;
        return occurTime.equals(that.occurTime);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + account.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + host.hashCode();
        result = 31 * result + methodName.hashCode();
        result = 31 * result + exception.hashCode();
        result = 31 * result + occurTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ErrLogModel{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", host='" + host + '\'' +
                ", methodName='" + methodName + '\'' +
                ", exception='" + exception + '\'' +
                ", occurTime=" + occurTime +
                '}';
    }

}
