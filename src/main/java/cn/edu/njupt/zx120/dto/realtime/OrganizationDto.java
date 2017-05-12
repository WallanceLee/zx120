package cn.edu.njupt.zx120.dto.realtime;

/**
 * Created by wallance on 4/13/17.
 */
public class OrganizationDto {

    private String orgId;

    private String orgName;

    private OrganizationPositionDto position;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public OrganizationPositionDto getPosition() {
        return position;
    }

    public void setPosition(OrganizationPositionDto position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationDto that = (OrganizationDto) o;

        if (!orgId.equals(that.orgId)) return false;
        if (!orgName.equals(that.orgName)) return false;
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        int result = orgId.hashCode();
        result = 31 * result + orgName.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrganizationDto{" +
                "orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", position=" + position +
                '}';
    }

}
