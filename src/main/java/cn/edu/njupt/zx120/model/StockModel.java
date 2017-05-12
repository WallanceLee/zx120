package cn.edu.njupt.zx120.model;

import javax.persistence.*;

/**
 * Created by wallance on 2/25/17.
 */
@Entity
@Table(name = "stock_info", schema = "zx120_test")
public class StockModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "container_id")
    private int containerId;

    @Column(name = "rfid_tag")
    private String rfidTag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getContainerId() {
        return containerId;
    }

    public void setContainerId(int contianer_id) {
        this.containerId = contianer_id;
    }

    public String getRfidTag() {
        return rfidTag;
    }

    public void setRfidTag(String rfidTag) {
        this.rfidTag = rfidTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StockModel that = (StockModel) o;

        if (id != that.id) return false;
        if (containerId != that.containerId) return false;
        return rfidTag.equals(that.rfidTag);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + containerId;
        result = 31 * result + rfidTag.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "StockModel{" +
                "id=" + id +
                ", contianer_id=" + containerId +
                ", rfidTag='" + rfidTag + '\'' +
                '}';
    }
}
