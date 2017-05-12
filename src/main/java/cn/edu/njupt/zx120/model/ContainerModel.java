package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 容器有关信息表
 * Created by wallance on 1/19/17.
 */
@Entity
@Table(name = "container_info", schema = "zx120_test")
public class ContainerModel implements Serializable {

    @Id
    @Column(name = "container_id")
    private long containerId;

    @Column(name = "container_name")
    private String containerName;

    @Column(name = "container_type")
    private int containerType;

    @Column(name = "belong_res_table")
    private String resourceTable;

    @Column(name = "belong_res_id")
    private String resourceId;

    @Column(name = "latest_scan_time")
    private Date lastScanTime;

    @Column(name = "scan_policy")
    private Integer scanPolicy;

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

    public int getContainerType() {
        return containerType;
    }

    public void setContainerType(int containerType) {
        this.containerType = containerType;
    }

    public String getResourceTable() {
        return resourceTable;
    }

    public void setResourceTable(String resourceTable) {
        this.resourceTable = resourceTable;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public Date getLastScanTime() {
        return lastScanTime;
    }

    public void setLastScanTime(Date lastScanTime) {
        this.lastScanTime = lastScanTime;
    }

    public int getScanPolicy() {
        return scanPolicy;
    }

    public void setScanPolicy(int scanPolicy) {
        this.scanPolicy = scanPolicy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerModel that = (ContainerModel) o;

        if (containerId != that.containerId) return false;
        if (containerType != that.containerType) return false;
        if (scanPolicy != that.scanPolicy) return false;
        if (!containerName.equals(that.containerName)) return false;
        if (!resourceTable.equals(that.resourceTable)) return false;
        if (!resourceId.equals(that.resourceId)) return false;
        return lastScanTime.equals(that.lastScanTime);
    }

    @Override
    public int hashCode() {
        int result = (int) (containerId ^ (containerId >>> 32));
        result = 31 * result + containerName.hashCode();
        result = 31 * result + containerType;
        result = 31 * result + resourceTable.hashCode();
        result = 31 * result + resourceId.hashCode();
        result = 31 * result + lastScanTime.hashCode();
        result = 31 * result + scanPolicy;
        return result;
    }

    @Override
    public String toString() {
        return "ContainerModel{" +
                "containerId=" + containerId +
                ", containerName='" + containerName + '\'' +
                ", containerType=" + containerType +
                ", resourceTable='" + resourceTable + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", lastScanTime=" + lastScanTime +
                ", scanPolicy=" + scanPolicy +
                '}';
    }

}
