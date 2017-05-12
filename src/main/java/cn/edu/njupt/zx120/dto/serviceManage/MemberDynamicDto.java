package cn.edu.njupt.zx120.dto.serviceManage;

import java.util.Date;

/**
 * Created by wallance on 4/18/17.
 */
public class MemberDynamicDto {

    private String memberName;

    private String containerName;

    private String desc;

    private Date time;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

        MemberDynamicDto that = (MemberDynamicDto) o;

        if (!memberName.equals(that.memberName)) return false;
        if (!containerName.equals(that.containerName)) return false;
        if (!desc.equals(that.desc)) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result = memberName.hashCode();
        result = 31 * result + containerName.hashCode();
        result = 31 * result + desc.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MemberDynamicDto{" +
                "memberName='" + memberName + '\'' +
                ", containerName='" + containerName + '\'' +
                ", desc='" + desc + '\'' +
                ", time=" + time +
                '}';
    }

}
