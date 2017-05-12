package cn.edu.njupt.zx120.dto.admin;

import java.util.Date;

/**
 * Created by wallance on 4/18/17.
 */
public class ReaderDto {

    private int readerId;

    private String orgId;

    private String department;

    private long containerId;

    private String containerName;

    private boolean activated;

    private Date activeTime;

    private Date shutdownTime;

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getContainerId() {
        return containerId;
    }

    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getShutdownTime() {
        return shutdownTime;
    }

    public void setShutdownTime(Date shutdownTime) {
        this.shutdownTime = shutdownTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReaderDto readerDto = (ReaderDto) o;

        if (readerId != readerDto.readerId) return false;
        if (containerId != readerDto.containerId) return false;
        if (activated != readerDto.activated) return false;
        if (!orgId.equals(readerDto.orgId)) return false;
        if (!department.equals(readerDto.department)) return false;
        if (!containerName.equals(readerDto.containerName)) return false;
        if (!activeTime.equals(readerDto.activeTime)) return false;
        return shutdownTime.equals(readerDto.shutdownTime);
    }

    @Override
    public int hashCode() {
        int result = readerId;
        result = 31 * result + orgId.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + (int) (containerId ^ (containerId >>> 32));
        result = 31 * result + containerName.hashCode();
        result = 31 * result + (activated ? 1 : 0);
        result = 31 * result + activeTime.hashCode();
        result = 31 * result + shutdownTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ReaderDto{" +
                "readerId=" + readerId +
                ", orgId='" + orgId + '\'' +
                ", department='" + department + '\'' +
                ", containerId=" + containerId +
                ", containerName='" + containerName + '\'' +
                ", activated=" + activated +
                ", activeTime=" + activeTime +
                ", shutdownTime=" + shutdownTime +
                '}';
    }

}
