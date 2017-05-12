package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wallance on 3/3/17.
 */
@Entity
@Table(name = "tag_info", schema = "zx120_test")
public class TagModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "vendor")
    private String vendor;

    @Column(name = "name")
    private String name;

    @Column(name = "tag_id")
    private String tagId;

    @Column(name = "state")
    private String state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagModel tagModel = (TagModel) o;

        if (id != tagModel.id) return false;
        if (!vendor.equals(tagModel.vendor)) return false;
        if (!name.equals(tagModel.name)) return false;
        if (!tagId.equals(tagModel.tagId)) return false;
        return state.equals(tagModel.state);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + vendor.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + tagId.hashCode();
        result = 31 * result + state.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TagModel{" +
                "id=" + id +
                ", vendor='" + vendor + '\'' +
                ", name='" + name + '\'' +
                ", tagId='" + tagId + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

}
