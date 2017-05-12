package cn.edu.njupt.zx120.dto.serviceManage;

/**
 * Created by wallance on 4/18/17.
 */
public class MemberDutyDto {

    private String memberName;

    private String department;

    private String memberType;

    private String containerName;

    private String duty;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberDutyDto that = (MemberDutyDto) o;

        if (!memberName.equals(that.memberName)) return false;
        if (!department.equals(that.department)) return false;
        if (!memberType.equals(that.memberType)) return false;
        if (!containerName.equals(that.containerName)) return false;
        return duty.equals(that.duty);
    }

    @Override
    public int hashCode() {
        int result = memberName.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + memberType.hashCode();
        result = 31 * result + containerName.hashCode();
        result = 31 * result + duty.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MemberDutyDto{" +
                "memberName='" + memberName + '\'' +
                ", department='" + department + '\'' +
                ", memberType='" + memberType + '\'' +
                ", containerName='" + containerName + '\'' +
                ", duty='" + duty + '\'' +
                '}';
    }

}
