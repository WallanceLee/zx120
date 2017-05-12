package cn.edu.njupt.zx120.dto.admin;

import java.util.Date;

/**
 * Created by wallance on 4/24/17.
 */
public class AmbulanceDto {

    private String carId;

    private String carBrand;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmbulanceDto that = (AmbulanceDto) o;

        if (!carId.equals(that.carId)) return false;
        return carBrand.equals(that.carBrand);
    }

    @Override
    public int hashCode() {
        int result = carId.hashCode();
        result = 31 * result + carBrand.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AmbulanceDto{" +
                "carId='" + carId + '\'' +
                ", carBrand='" + carBrand + '\'' +
                '}';
    }

}
