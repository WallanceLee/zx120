package cn.edu.njupt.zx120.model;

import javax.persistence.*;

/**
 * Created by wallance on 3/18/17.
 */
@Entity
@Table(name = "user_authority", schema = "zx120_test")
public class UserAuthorityModel {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "authority_id")
    private int authorityId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(int authorityId) {
        this.authorityId = authorityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthorityModel that = (UserAuthorityModel) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        return authorityId == that.authorityId;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + authorityId;
        return result;
    }

    @Override
    public String toString() {
        return "UserAuthorityModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", authorityId=" + authorityId +
                '}';
    }

}
