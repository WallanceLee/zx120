package cn.edu.njupt.zx120.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by wallance on 3/15/17.
 */
@Entity
@Table(name = "sys_org_info", schema = "zx120_test", catalog = "")
public class OrganizationModel {
    private String orgId;
    private String name;
    private Integer type;
    private String orgCode;
    private Integer adminCode;
    private Byte scheduleFlag;
    private Short orgLevel;
    private String address;
    private String phoneNumber;
    private Byte onlineFlag;
    private Timestamp createTime;
    private Timestamp lastModifyTime;

    @Id
    @Column(name = "org_id")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "ssjgdm")
    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    @Basic
    @Column(name = "xzbm")
    public Integer getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(Integer xzbm) {
        this.adminCode = xzbm;
    }

    @Basic
    @Column(name = "ywddq")
    public Byte getScheduleFlag() {
        return scheduleFlag;
    }

    public void setScheduleFlag(Byte scheduleFlag) {
        this.scheduleFlag = scheduleFlag;
    }

    @Basic
    @Column(name = "jgdj")
    public Short getOrgLevel() {
        return orgLevel;
    }

    public void setOrgLevel(Short orgLevel) {
        this.orgLevel = orgLevel;
    }

    @Basic
    @Column(name = "dz")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "lxdh")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "sfzx")
    public Byte getOnlineFlag() {
        return onlineFlag;
    }

    public void setOnlineFlag(Byte onlineFlag) {
        this.onlineFlag = onlineFlag;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "last_modify_time")
    public Timestamp getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Timestamp lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationModel that = (OrganizationModel) o;

        if (orgId != null ? !orgId.equals(that.orgId) : that.orgId != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (orgCode != null ? !orgCode.equals(that.orgCode) : that.orgCode != null) return false;
        if (adminCode != null ? !adminCode.equals(that.adminCode) : that.adminCode != null) return false;
        if (scheduleFlag != null ? !scheduleFlag.equals(that.scheduleFlag) : that.scheduleFlag != null) return false;
        if (orgLevel != null ? !orgLevel.equals(that.orgLevel) : that.orgLevel != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;
        if (onlineFlag != null ? !onlineFlag.equals(that.onlineFlag) : that.onlineFlag != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (lastModifyTime != null ? !lastModifyTime.equals(that.lastModifyTime) : that.lastModifyTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orgId != null ? orgId.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (orgCode != null ? orgCode.hashCode() : 0);
        result = 31 * result + (adminCode != null ? adminCode.hashCode() : 0);
        result = 31 * result + (scheduleFlag != null ? scheduleFlag.hashCode() : 0);
        result = 31 * result + (orgLevel != null ? orgLevel.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + (onlineFlag != null ? onlineFlag.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastModifyTime != null ? lastModifyTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrganizationModel{" +
                "orgId='" + orgId + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", orgCode='" + orgCode + '\'' +
                ", adminCode=" + adminCode +
                ", scheduleFlag=" + scheduleFlag +
                ", orgLevel=" + orgLevel +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", onlineFlag=" + onlineFlag +
                ", createTime=" + createTime +
                ", lastModifyTime=" + lastModifyTime +
                '}';
    }

}
