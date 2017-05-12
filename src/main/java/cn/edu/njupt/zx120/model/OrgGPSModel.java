package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wallance on 4/14/17.
 */
@Entity
@Table(name = "org_gps", schema = "zx120_test")
public class OrgGPSModel {

    @Id
    @Column(name = "org_id")
    private String orgId;

    @Column(name = "org_name")
    private String orgName;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "latitude")
    private String latitude;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longtitude) {
        this.longitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrgGPSModel that = (OrgGPSModel) o;

        if (!orgId.equals(that.orgId)) return false;
        if (!orgName.equals(that.orgName)) return false;
        if (!longitude.equals(that.longitude)) return false;
        return latitude.equals(that.latitude);
    }

    @Override
    public int hashCode() {
        int result = orgId.hashCode();
        result = 31 * result + orgName.hashCode();
        result = 31 * result + longitude.hashCode();
        result = 31 * result + latitude.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrgGPSModel{" +
                "orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", longtitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

}
