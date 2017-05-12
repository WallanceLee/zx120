package cn.edu.njupt.zx120.dto.admin;

/**
 * Created by wallance on 4/24/17.
 */
public class ContainerTableTypeDto {

    private String tableName;

    private String resourceType;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerTableTypeDto that = (ContainerTableTypeDto) o;

        if (!tableName.equals(that.tableName)) return false;
        return resourceType.equals(that.resourceType);
    }

    @Override
    public int hashCode() {
        int result = tableName.hashCode();
        result = 31 * result + resourceType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ContainerTableTypeDto{" +
                "tableName='" + tableName + '\'' +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }

}
