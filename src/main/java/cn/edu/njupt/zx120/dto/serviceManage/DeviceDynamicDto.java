package cn.edu.njupt.zx120.dto.serviceManage;

import java.util.Date;

/**
 * Created by wallance on 4/18/17.
 */
public class DeviceDynamicDto {

    private String deviceName;

    private String containerName;

    private String eventType;

    private Date time;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
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

        DeviceDynamicDto that = (DeviceDynamicDto) o;

        if (!deviceName.equals(that.deviceName)) return false;
        if (!containerName.equals(that.containerName)) return false;
        if (!eventType.equals(that.eventType)) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result = deviceName.hashCode();
        result = 31 * result + containerName.hashCode();
        result = 31 * result + eventType.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "DeviceDynamicDto{" +
                "deviceName='" + deviceName + '\'' +
                ", containerName='" + containerName + '\'' +
                ", eventType='" + eventType + '\'' +
                ", time=" + time +
                '}';
    }

}
