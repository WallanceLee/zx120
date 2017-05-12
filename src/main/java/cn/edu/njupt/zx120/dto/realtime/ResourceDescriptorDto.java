package cn.edu.njupt.zx120.dto.realtime;

/**
 * Created by wallance on 4/13/17.
 */
public class ResourceDescriptorDto {

    private long resourceTotalNumber;

    private long resourceRemainNumber;

    private long resourceOutNumber;

    public long getResourceTotalNumber() {
        return resourceTotalNumber;
    }

    public void setResourceTotalNumber(long resourceTotalNumber) {
        this.resourceTotalNumber = resourceTotalNumber;
    }

    public long getResourceRemainNumber() {
        return resourceRemainNumber;
    }

    public void setResourceRemainNumber(long resourceRemainNumber) {
        this.resourceRemainNumber = resourceRemainNumber;
    }

    public long getResourceOutNumber() {
        return resourceOutNumber;
    }

    public void setResourceOutNumber(long resourceOutNumber) {
        this.resourceOutNumber = resourceOutNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResourceDescriptorDto that = (ResourceDescriptorDto) o;

        if (resourceTotalNumber != that.resourceTotalNumber) return false;
        if (resourceRemainNumber != that.resourceRemainNumber) return false;
        return resourceOutNumber == that.resourceOutNumber;
    }

    @Override
    public int hashCode() {
        int result = (int) (resourceTotalNumber ^ (resourceTotalNumber >>> 32));
        result = 31 * result + (int) (resourceRemainNumber ^ (resourceRemainNumber >>> 32));
        result = 31 * result + (int) (resourceOutNumber ^ (resourceOutNumber >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ResourceDescriptorDto{" +
                "resourceTotalNumber=" + resourceTotalNumber +
                ", resourceRemainNumber=" + resourceRemainNumber +
                ", resourceOutNumber=" + resourceOutNumber +
                '}';
    }

}
