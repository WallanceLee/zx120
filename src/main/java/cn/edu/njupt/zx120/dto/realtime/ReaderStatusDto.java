package cn.edu.njupt.zx120.dto.realtime;

/**
 * Created by wallance on 4/18/17.
 */
public class ReaderStatusDto {

    private String department;

    private String containerName;

    private String readerId;

    private String status;

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

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReaderStatusDto that = (ReaderStatusDto) o;

        if (!department.equals(that.department)) return false;
        if (!containerName.equals(that.containerName)) return false;
        if (!readerId.equals(that.readerId)) return false;
        return status.equals(that.status);
    }

    @Override
    public int hashCode() {
        int result = department.hashCode();
        result = 31 * result + containerName.hashCode();
        result = 31 * result + readerId.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ReaderStatusDto{" +
                "department='" + department + '\'' +
                ", containerName='" + containerName + '\'' +
                ", readerId='" + readerId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}
