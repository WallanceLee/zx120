package cn.edu.njupt.zx120.dto.realtime;

import java.util.Date;

/**
 * Created by wallance on 4/14/17.
 */
public class RecordLogDto {

    private int recordId;

    private String readerId;

    private Date storeTime;

    private String rfidTag;

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getReaderId() {
        return readerId;
    }

    public void setReaderId(String readerId) {
        this.readerId = readerId;
    }

    public Date getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(Date storeTime) {
        this.storeTime = storeTime;
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

        RecordLogDto that = (RecordLogDto) o;

        if (recordId != that.recordId) return false;
        if (!readerId.equals(that.readerId)) return false;
        if (!storeTime.equals(that.storeTime)) return false;
        return rfidTag.equals(that.rfidTag);
    }

    @Override
    public int hashCode() {
        int result = recordId;
        result = 31 * result + readerId.hashCode();
        result = 31 * result + storeTime.hashCode();
        result = 31 * result + rfidTag.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "RecordLogDto{" +
                "recordId=" + recordId +
                ", readerId='" + readerId + '\'' +
                ", storeTime=" + storeTime +
                ", rfidTag='" + rfidTag + '\'' +
                '}';
    }

}
