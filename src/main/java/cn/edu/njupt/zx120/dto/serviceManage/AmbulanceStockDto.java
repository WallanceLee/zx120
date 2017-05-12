package cn.edu.njupt.zx120.dto.serviceManage;

import java.util.Date;

/**
 * Created by wallance on 4/11/17.
 */
public class AmbulanceStockDto {

    private String carId;

    private String carBrand;

    private String department;

    private String containerName;

    private Date time;

    private String lastPosition;

    public AmbulanceStockDto() {
    }

    public AmbulanceStockDto(String carId, String carBrand, String department, String containerName) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.department = department;
        this.containerName = containerName;
    }

    public AmbulanceStockDto(String carId, String carBrand, String department, String containerName, Date time, String lastPosition) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.department = department;
        this.containerName = containerName;
        this.time = time;
        this.lastPosition = lastPosition;
    }

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(String lastPosition) {
        this.lastPosition = lastPosition;
    }

}
