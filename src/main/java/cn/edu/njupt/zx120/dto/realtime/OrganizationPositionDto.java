package cn.edu.njupt.zx120.dto.realtime;

/**
 * Created by wallance on 4/13/17.
 */
public class OrganizationPositionDto {

    // 经度
    private String longitude;

    // 维度
    private String latitude;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationPositionDto that = (OrganizationPositionDto) o;

        if (!longitude.equals(that.longitude)) return false;
        return latitude.equals(that.latitude);
    }

    @Override
    public int hashCode() {
        int result = longitude.hashCode();
        result = 31 * result + latitude.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "OrganizationPositionDto{" +
                "longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                '}';
    }

}
