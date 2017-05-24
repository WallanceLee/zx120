package cn.edu.njupt.zx120.model;

import javax.persistence.*;

/**
 * Created by wallance on 17-5-19.
 */
@Entity
@Table(name = "ambul_stock_detail", schema = "zx120_test")
public class AmbulanceStockDetailModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "ambul_org")
    private String ambulanceOrg;

    @Column(name = "ambul_org_name")
    private String ambulanceOrgName;

    @Column(name = "cph")
    private String carBrand;

    @Column(name = "container_id")
    private long containerId;

    @Column(name = "trigger_event")
    private int triggerEvent;

    @Column(name = "container_org")
    private String containerOrg;

    @Column(name = "container_org_name")
    private String containerOrgName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmbulanceOrg() {
        return ambulanceOrg;
    }

    public void setAmbulanceOrg(String ambulanceOrg) {
        this.ambulanceOrg = ambulanceOrg;
    }

    public String getAmbulanceOrgName() {
        return ambulanceOrgName;
    }

    public void setAmbulanceOrgName(String ambulanceOrgName) {
        this.ambulanceOrgName = ambulanceOrgName;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public long getContainerId() {
        return containerId;
    }

    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    public int getTriggerEvent() {
        return triggerEvent;
    }

    public void setTriggerEvent(int triggerEvent) {
        this.triggerEvent = triggerEvent;
    }

    public String getContainerOrg() {
        return containerOrg;
    }

    public void setContainerOrg(String containerOrg) {
        this.containerOrg = containerOrg;
    }

    public String getContainerOrgName() {
        return containerOrgName;
    }

    public void setContainerOrgName(String containerOrgName) {
        this.containerOrgName = containerOrgName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmbulanceStockDetailModel that = (AmbulanceStockDetailModel) o;

        if (id != that.id) return false;
        if (containerId != that.containerId) return false;
        if (triggerEvent != that.triggerEvent) return false;
        if (!ambulanceOrg.equals(that.ambulanceOrg)) return false;
        if (!ambulanceOrgName.equals(that.ambulanceOrgName)) return false;
        if (!carBrand.equals(that.carBrand)) return false;
        if (!containerOrg.equals(that.containerOrg)) return false;
        return containerOrgName.equals(that.containerOrgName);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ambulanceOrg.hashCode();
        result = 31 * result + ambulanceOrgName.hashCode();
        result = 31 * result + carBrand.hashCode();
        result = 31 * result + (int) (containerId ^ (containerId >>> 32));
        result = 31 * result + triggerEvent;
        result = 31 * result + containerOrg.hashCode();
        result = 31 * result + containerOrgName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AmbulanceStockDetailModel{" +
                "id=" + id +
                ", ambulanceOrg='" + ambulanceOrg + '\'' +
                ", ambulanceOrgName='" + ambulanceOrgName + '\'' +
                ", carBrand='" + carBrand + '\'' +
                ", containerId=" + containerId +
                ", triggerEvent=" + triggerEvent +
                ", containerOrg='" + containerOrg + '\'' +
                ", containerOrgName='" + containerOrgName + '\'' +
                '}';
    }

}
