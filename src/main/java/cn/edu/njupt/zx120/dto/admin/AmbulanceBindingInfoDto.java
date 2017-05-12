package cn.edu.njupt.zx120.dto.admin;

import java.util.Date;

/**
 * Created by wallance on 4/21/17.
 */
public class AmbulanceBindingInfoDto {

    private String carId;

    private String carBrand;

    private String orgName;

    private String rfidTag;

    private Date time;

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getRfidTag() {
        return rfidTag;
    }

    public void setRfidTag(String rfidTag) {
        this.rfidTag = rfidTag;
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

        AmbulanceBindingInfoDto that = (AmbulanceBindingInfoDto) o;

        if (!carId.equals(that.carId)) return false;
        if (!carBrand.equals(that.carBrand)) return false;
        if (!orgName.equals(that.orgName)) return false;
        if (!rfidTag.equals(that.rfidTag)) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result = carId.hashCode();
        result = 31 * result + carBrand.hashCode();
        result = 31 * result + orgName.hashCode();
        result = 31 * result + rfidTag.hashCode();
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AmbulanceBindingInfoDto{" +
                "carId='" + carId + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", orgName='" + orgName + '\'' +
                ", rfidTag='" + rfidTag + '\'' +
                ", time=" + time +
                '}';
    }

}
