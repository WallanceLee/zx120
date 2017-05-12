package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 设备ORM
 * id: 设备流水号
 * department: 设备所属的部门
 * deviceName: 设备名称
 * Created by wallance on 2/25/17.
 */
@Entity
@Table(name = "device_info", schema = "zx120_test")
public class DeviceModel {

    @Column(name = "department")
    private String department;

    @Column(name = "name")
    private String deviceName;

    @Id
    @Column(name = "device_id")
    private String deviceId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "last_modify_time")
    private Date lastModifyTime;

    @Column(name = "org_id")
    private String orgId;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceModel that = (DeviceModel) o;

        if (!department.equals(that.department)) return false;
        if (!deviceName.equals(that.deviceName)) return false;
        if (!deviceId.equals(that.deviceId)) return false;
        if (!createTime.equals(that.createTime)) return false;
        if (!lastModifyTime.equals(that.lastModifyTime)) return false;
        return orgId.equals(that.orgId);
    }

    @Override
    public int hashCode() {
        int result = department.hashCode();
        result = 31 * result + deviceName.hashCode();
        result = 31 * result + deviceId.hashCode();
        result = 31 * result + createTime.hashCode();
        result = 31 * result + lastModifyTime.hashCode();
        result = 31 * result + orgId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DeviceModel{" +
                "department='" + department + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", createTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                ", orgId='" + orgId + '\'' +
                '}';
    }

}
