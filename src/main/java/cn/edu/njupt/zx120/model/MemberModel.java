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
import java.util.Date;

/**
 * 医护人员信息表
 * Created by wallance on 1/18/17.
 */
@Entity
@Table(name = "sys_member_info", schema = "zx120_test")
public class MemberModel implements Serializable {

    @Id
    @Column(name = "id")
    private String memberId;

    @Column(name = "name")
    private String name;

    // 人员职务
    @Column(name = "type")
    private String type;

    @Column(name = "org_id", nullable = false)
    private String orgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "fk_member_org"))
    private OrganizationModel organizationModel;

    // 人员本身属于什么部门
    @Column(name = "ssks")
    private String department;

    // 工号是否注销
    @Column(name = "flag")
    private boolean flag;

    // 人员电话号码
    @Column(name = "phone_num")
    private String phoneNumber;

    // 人员短信号码
    @Column(name = "text_num")
    private String smsNumber;

    // 创建时间
    @Column(name = "create_time")
    private Date createTime;

    // 上次修改时间
    @Column(name = "last_modify_time")
    private Date lastModifiedTime;

    // 行政编码
    @Column(name = "xzbm")
    private int adminCode;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String id) {
        this.memberId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgIdNow) {
        this.orgId = orgIdNow;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSmsNumber() {
        return smsNumber;
    }

    public void setSmsNumber(String smsNumber) {
        this.smsNumber = smsNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public int getAdminCode() {
        return adminCode;
    }

    public void setAdminCode(int adminCode) {
        this.adminCode = adminCode;
    }

    public OrganizationModel getOrganizationModel() {
        return organizationModel;
    }

    public void setOrganizationModel(OrganizationModel organizationModel) {
        this.organizationModel = organizationModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberModel that = (MemberModel) o;

        if (flag != that.flag) return false;
        if (adminCode != that.adminCode) return false;
        if (!memberId.equals(that.memberId)) return false;
        if (!name.equals(that.name)) return false;
        if (!type.equals(that.type)) return false;
        if (!orgId.equals(that.orgId)) return false;
        if (!organizationModel.equals(that.organizationModel)) return false;
        if (!department.equals(that.department)) return false;
        if (!phoneNumber.equals(that.phoneNumber)) return false;
        if (!smsNumber.equals(that.smsNumber)) return false;
        if (!createTime.equals(that.createTime)) return false;
        return lastModifiedTime.equals(that.lastModifiedTime);
    }

    @Override
    public int hashCode() {
        int result = memberId.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + orgId.hashCode();
        result = 31 * result + organizationModel.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + (flag ? 1 : 0);
        result = 31 * result + phoneNumber.hashCode();
        result = 31 * result + smsNumber.hashCode();
        result = 31 * result + createTime.hashCode();
        result = 31 * result + lastModifiedTime.hashCode();
        result = 31 * result + adminCode;
        return result;
    }

    @Override
    public String toString() {
        return "MemberModel{" +
                "id='" + memberId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", orgId='" + orgId + '\'' +
                ", organizationModel=" + organizationModel +
                ", department='" + department + '\'' +
                ", flag=" + flag +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", smsNumber='" + smsNumber + '\'' +
                ", createTime=" + createTime +
                ", lastModifiedTime=" + lastModifiedTime +
                ", adminCode=" + adminCode +
                '}';
    }

}
