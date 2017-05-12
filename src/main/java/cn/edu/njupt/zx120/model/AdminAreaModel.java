package cn.edu.njupt.zx120.model;

import javax.persistence.*;

/**
 * Created by wallance on 3/19/17.
 */
@Entity
@Table(name = "admin_code", schema = "zx120_test")
public class AdminAreaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "city", nullable = false, updatable = false, insertable = false)
    private String city;

    @Column(name = "code", nullable = false, updatable = false, insertable = false)
    private int code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdminAreaModel that = (AdminAreaModel) o;

        if (id != that.id) return false;
        if (code != that.code) return false;
        return city.equals(that.city);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + city.hashCode();
        result = 31 * result + code;
        return result;
    }

    @Override
    public String toString() {
        return "AdminAreaModel{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", code=" + code +
                '}';
    }

}
