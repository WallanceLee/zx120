package cn.edu.njupt.zx120.dto.serviceManage;

import java.util.Date;

/**
 * Created by wallance on 4/11/17.
 */
public class AmbulanceDynamicDto {

    private String carId;

    private String carBrand;

    private String containerName;

    private String eventType;

    private Date time;

    public AmbulanceDynamicDto() {
    }

    public AmbulanceDynamicDto(String carId, String carBrand, String containerName, String eventType, Date time) {
        this.carId = carId;
        this.carBrand = carBrand;
        this.containerName = containerName;
        this.eventType = eventType;
        this.time = time;
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

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
