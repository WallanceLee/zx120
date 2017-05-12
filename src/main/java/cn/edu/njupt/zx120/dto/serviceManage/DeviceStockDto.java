package cn.edu.njupt.zx120.dto.serviceManage;

import java.util.Date;

/**
 * Created by wallance on 4/18/17.
 */
public class DeviceStockDto {

    private String deviceName;

    private String department;

    private String containerName;

    private Date time;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
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

        DeviceStockDto that = (DeviceStockDto) o;

        if (!deviceName.equals(that.deviceName)) return false;
        if (!department.equals(that.department)) return false;
        if (!containerName.equals(that.containerName)) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result = deviceName.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + containerName.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DeviceStockDto{" +
                "deviceName='" + deviceName + '\'' +
                ", department='" + department + '\'' +
                ", containerName='" + containerName + '\'' +
                ", time=" + time +
                '}';
    }

}
