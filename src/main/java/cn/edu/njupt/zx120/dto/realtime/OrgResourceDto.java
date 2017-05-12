package cn.edu.njupt.zx120.dto.realtime;

/**
 * Created by wallance on 4/13/17.
 */
public class OrgResourceDto {

    private OrganizationDto org;

    private ResourceDescriptorDto memberResource;

    private ResourceDescriptorDto ambulanceResource;

    public OrganizationDto getOrg() {
        return org;
    }

    public void setOrg(OrganizationDto org) {
        this.org = org;
    }

    public ResourceDescriptorDto getMemberResource() {
        return memberResource;
    }

    public void setMemberResource(ResourceDescriptorDto memberResource) {
        this.memberResource = memberResource;
    }

    public ResourceDescriptorDto getAmbulanceResource() {
        return ambulanceResource;
    }

    public void setAmbulanceResource(ResourceDescriptorDto ambulanceResource) {
        this.ambulanceResource = ambulanceResource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrgResourceDto that = (OrgResourceDto) o;

        if (!org.equals(that.org)) return false;
        if (!memberResource.equals(that.memberResource)) return false;
        return ambulanceResource.equals(that.ambulanceResource);
    }

    @Override
    public int hashCode() {
        int result = org.hashCode();
        result = 31 * result + memberResource.hashCode();
        result = 31 * result + ambulanceResource.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrgResourceDto{" +
                "org=" + org +
                ", memberResource=" + memberResource +
                ", ambulanceResource=" + ambulanceResource +
                '}';
    }

}
