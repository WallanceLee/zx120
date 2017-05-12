package cn.edu.njupt.zx120.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wallance on 2/22/17.
 */
@Entity
@Table(name = "oper_log", schema = "zx120_test")
public class OperLogModel implements Serializable {

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

    @Column(name = "spent_time")
    private int spentTime;

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

    public int getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(int spentTime) {
        this.spentTime = spentTime;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    @Override
    public String toString() {
        return "OperLogModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", host='" + host + '\'' +
                ", methodName='" + methodName + '\'' +
                ", spentTime=" + spentTime +
                ", occurTime=" + occurTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperLogModel that = (OperLogModel) o;

        if (id != that.id) return false;
        if (spentTime != that.spentTime) return false;
        if (!account.equals(that.account)) return false;
        if (!username.equals(that.username)) return false;
        if (!host.equals(that.host)) return false;
        if (!methodName.equals(that.methodName)) return false;
        return occurTime.equals(that.occurTime);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + account.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + host.hashCode();
        result = 31 * result + methodName.hashCode();
        result = 31 * result + spentTime;
        result = 31 * result + occurTime.hashCode();
        return result;
    }
}
