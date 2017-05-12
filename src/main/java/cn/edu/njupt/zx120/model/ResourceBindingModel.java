package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 资源绑定信息表
 * Created by wallance on 1/18/17.
 */
@Entity
@Table(name = "resource_bind", schema = "zx120_test", catalog = "")
public class ResourceBindingModel implements Serializable {

    @Id
    @Column(name = "res_bind_id")
    private int resourceBindId;

    @Column(name = "res_table")
    private String resourceTableName;

    @Column(name = "res_id_in_table")
    private String resourceId;

    @Column(name = "res_name")
    private String resourceName;

    @Column(name = "res_type")
    private String resourceType;

    @Column(name = "res_notes")
    private String notes;

    @Column(name = "res_tag")
    private String tag;

    @Column(name = "res_bind_time")
    private Date bindTime;

    public int getResourceBindId() {
        return resourceBindId;
    }

    public void setResourceBindId(int resourceBindId) {
        this.resourceBindId = resourceBindId;
    }

    public String getResourceTableName() {
        return resourceTableName;
    }

    public void setResourceTableName(String resourceTableName) {
        this.resourceTableName = resourceTableName;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getBindTime() {
        return bindTime;
    }

    public void setBindTime(Date bindTime) {
        this.bindTime = bindTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceBindingModel that = (ResourceBindingModel) o;

        if (resourceBindId != that.resourceBindId) return false;
        if (resourceId != that.resourceId) return false;
        if (!resourceTableName.equals(that.resourceTableName)) return false;
        if (!resourceName.equals(that.resourceName)) return false;
        if (!resourceType.equals(that.resourceType)) return false;
        if (!notes.equals(that.notes)) return false;
        if (!tag.equals(that.tag)) return false;
        return bindTime.equals(that.bindTime);
    }

    @Override
    public int hashCode() {
        int result = resourceBindId;
        result = 31 * result + resourceTableName.hashCode();
        result = 31 * result + resourceId.hashCode();
        result = 31 * result + resourceName.hashCode();
        result = 31 * result + resourceType.hashCode();
        result = 31 * result + notes.hashCode();
        result = 31 * result + tag.hashCode();
        result = 31 * result + bindTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ResourceBindingModel{" +
                "resourceBindId=" + resourceBindId +
                ", resourceTableName='" + resourceTableName + '\'' +
                ", resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", resourceType='" + resourceType + '\'' +
                ", notes='" + notes + '\'' +
                ", tag='" + tag + '\'' +
                ", bindTime=" + bindTime +
                '}';
    }

}
