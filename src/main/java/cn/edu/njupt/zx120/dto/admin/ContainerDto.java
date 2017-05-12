package cn.edu.njupt.zx120.dto.admin;

import java.util.Date;

/**
 * Created by wallance on 4/18/17.
 */
public class ContainerDto {

    private long containerId;

    private String department;

    private String containerName;

    private Date lastScanTime;

    public long getContainerId() {
        return containerId;
    }

    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public Date getLastScanTime() {
        return lastScanTime;
    }

    public void setLastScanTime(Date lastScanTime) {
        this.lastScanTime = lastScanTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerDto that = (ContainerDto) o;

        if (containerId != that.containerId) return false;
        if (!department.equals(that.department)) return false;
        if (!containerName.equals(that.containerName)) return false;
        return lastScanTime.equals(that.lastScanTime);
    }

    @Override
    public int hashCode() {
        int result = (int) (containerId ^ (containerId >>> 32));
        result = 31 * result + department.hashCode();
        result = 31 * result + containerName.hashCode();
        result = 31 * result + lastScanTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ContainerDto{" +
                "containerId=" + containerId +
                ", department='" + department + '\'' +
                ", containerName='" + containerName + '\'' +
                ", lastScanTime=" + lastScanTime +
                '}';
    }

}
