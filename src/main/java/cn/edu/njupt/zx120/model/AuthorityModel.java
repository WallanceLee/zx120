package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by wallance on 1/13/17.
 * 这是有关授权信息的数据表实体类，用于实现ORM
 */
@Entity
@Table(name = "authorities", schema = "zx120_test")
public class AuthorityModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private long id;

    @Column(name = "type", nullable = false, length = 30)
    private String type = AuthorityTypeEnum.USER.getAuthorityType();

    @Column(name = "department")
    private String department;

    @Column(name = "function")
    private String function;

    @Column(name = "param")
    private String param;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorityModel that = (AuthorityModel) o;

        if (id != that.id) return false;
        if (!type.equals(that.type)) return false;
        if (!department.equals(that.department)) return false;
        if (!function.equals(that.function)) return false;
        return param.equals(that.param);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + type.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + function.hashCode();
        result = 31 * result + param.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AuthorityModel{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", department='" + department + '\'' +
                ", function='" + function + '\'' +
                ", param='" + param + '\'' +
                '}';
    }

}
