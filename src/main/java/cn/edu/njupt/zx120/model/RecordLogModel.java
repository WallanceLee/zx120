package cn.edu.njupt.zx120.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wallance on 4/11/17.
 */
@Entity
@Table(name = "record_log", schema = "zx120_test")
public class RecordLogModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "readerid")
    private String readerId;

    @Column(name = "rfid_tag")
    private String rfidTag;

    @Column(name = "record_time")
    private Date recordTime;

    @Column(name = "store_time")
    private Date storeTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public String getRfidTag() {
        return rfidTag;
    }

    public void setRfidTag(String rfidTag) {
        this.rfidTag = rfidTag;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecordLogModel that = (RecordLogModel) o;

        if (id != that.id) return false;
        if (!readerId.equals(that.readerId)) return false;
        if (!rfidTag.equals(that.rfidTag)) return false;
        if (!recordTime.equals(that.recordTime)) return false;
        return storeTime.equals(that.storeTime);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + readerId.hashCode();
        result = 31 * result + rfidTag.hashCode();
        result = 31 * result + recordTime.hashCode();
        result = 31 * result + storeTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RecordLogModel{" +
                "id=" + id +
                ", readerId='" + readerId + '\'' +
                ", rfidTag='" + rfidTag + '\'' +
                ", recordTime=" + recordTime +
                ", storeTime=" + storeTime +
                '}';
    }

}
