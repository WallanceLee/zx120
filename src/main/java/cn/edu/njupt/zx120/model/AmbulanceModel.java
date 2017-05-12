package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 救护车信息表
 * Created by wallance on 1/18/17.
 */
@Entity
@Table(name = "sys_ambul_info", schema = "zx120_test")
public class    AmbulanceModel implements Serializable {

    @Id
    @Column(name = "clid", nullable = false)
    private String carId;

    @Column(name = "org_id", nullable = false)
    private String orgId;

    @Column(name = "cph", nullable = false, unique = true)
    private String carBrand;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "flag", nullable = false)
    private boolean flag;

    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;

    @Column(name = "last_modify_time", nullable = false)
    private Timestamp lastModifiedTime;

    @Column(name = "xzbm", nullable = false)
    private int adminCode;

    @Column(name = "car_phone", nullable = false)
    private String carPhone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", updatable = false, insertable = false,
            foreignKey = @ForeignKey(name = "fk_ambul_org"))
    private OrganizationModel organizationModel;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(int adminCode) {
        this.adminCode = adminCode;
    }

    public String getCarPhone() {
        return carPhone;
    }

    public void setCarPhone(String carPhone) {
        this.carPhone = carPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmbulanceModel that = (AmbulanceModel) o;

        if (flag != that.flag) return false;
        if (adminCode != that.adminCode) return false;
        if (!carId.equals(that.carId)) return false;
        if (!orgId.equals(that.orgId)) return false;
        if (!carBrand.equals(that.carBrand)) return false;
        if (!name.equals(that.name)) return false;
        if (!createTime.equals(that.createTime)) return false;
        if (!lastModifiedTime.equals(that.lastModifiedTime)) return false;
        if (!carPhone.equals(that.carPhone)) return false;
        return organizationModel.equals(that.organizationModel);
    }

    @Override
    public int hashCode() {
        int result = carId.hashCode();
        result = 31 * result + orgId.hashCode();
        result = 31 * result + carBrand.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (flag ? 1 : 0);
        result = 31 * result + createTime.hashCode();
        result = 31 * result + lastModifiedTime.hashCode();
        result = 31 * result + adminCode;
        result = 31 * result + carPhone.hashCode();
        result = 31 * result + organizationModel.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AmbulanceModel{" +
                "carId='" + carId + '\'' +
                ", orgId='" + orgId + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                ", createTime=" + createTime +
                ", lastModifiedTime=" + lastModifiedTime +
                ", adminCode=" + adminCode +
                ", carPhone='" + carPhone + '\'' +
                ", organizationModel=" + organizationModel +
                '}';
    }

}
