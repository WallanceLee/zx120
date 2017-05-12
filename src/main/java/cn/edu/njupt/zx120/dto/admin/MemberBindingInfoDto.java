package cn.edu.njupt.zx120.dto.admin;

import java.util.Date;

/**
 * Created by wallance on 4/18/17.
 */
public class    MemberBindingInfoDto {

    private String memberId;

    private String memberName;

    private String orgName;

    private String department;

    private String tag;

    private Date time;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberBindingInfoDto that = (MemberBindingInfoDto) o;

        if (!memberId.equals(that.memberId)) return false;
        if (!memberName.equals(that.memberName)) return false;
        if (!orgName.equals(that.orgName)) return false;
        if (!department.equals(that.department)) return false;
        if (!tag.equals(that.tag)) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result = memberId.hashCode();
        result = 31 * result + memberName.hashCode();
        result = 31 * result + orgName.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + tag.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MemberBindingInfoDto{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", orgName='" + orgName + '\'' +
                ", department='" + department + '\'' +
                ", tag='" + tag + '\'' +
                ", time=" + time +
                '}';
    }

}
