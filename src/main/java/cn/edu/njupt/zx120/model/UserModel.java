package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wallance on 1/13/17.
 * 这是用户信息的数据表映射实体类
 * 用于实现ORM
 * 其中的authorities使用多对多关系表示，
 * 用于体现数据表的外键
 * 一个用户可以有多个授权
 * 一个授权也可以对应多个用户
 */
@Entity
@Table(name = "users", schema = "zx120_test", catalog = "")
public class UserModel implements Serializable {

    @Column(name = "account", nullable = false)
    private String account;

    @Column(name = "username", nullable = false, length = 20, unique = true)
    private String username;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "credentials_salt")
    private String credentialsSalt;

    @Column(name = "department")
    private String department;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "authority_id") })
    private List<AuthorityModel> authorities = new ArrayList<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<AuthorityModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityModel> authorities) {
        this.authorities = authorities;
    }

    public String getCredentialsSalt() {
        return credentialsSalt;
    }

    public void setCredentialsSalt(String credentialsSalt) {
        this.credentialsSalt = credentialsSalt;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (id != userModel.id) return false;
        if (!account.equals(userModel.account)) return false;
        if (!username.equals(userModel.username)) return false;
        if (!password.equals(userModel.password)) return false;
        if (!credentialsSalt.equals(userModel.credentialsSalt)) return false;
        if (!department.equals(userModel.department)) return false;
        return authorities.equals(userModel.authorities);
    }

    @Override
    public int hashCode() {
        int result = account.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (int) (id ^ (id >>> 32));
        result = 31 * result + credentialsSalt.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + authorities.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "account='" + account + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                ", credentialsSalt='" + credentialsSalt + '\'' +
                ", department='" + department + '\'' +
                ", authorities=" + authorities +
                '}';
    }

}
