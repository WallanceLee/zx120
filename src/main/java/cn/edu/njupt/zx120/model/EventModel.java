package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 事件信息表
 * Created by wallance on 1/19/17.
 */
@Entity
@Table(name = "event_info")
public class EventModel implements Serializable {

    @Id
    @Column(name = "event_id")
    private int eventId;

    @Column(name = "container_id")
    private long containerId;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "event_time")
    private Date eventTime;

    @Column(name = "event_res_table")
    private String eventResourceTable;

    @Column(name = "event_res_id")
    private String eventResourceId;

    @Column(name = "event_desc")
    private String eventDescription;

    public EventModel() {
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public long getContainerId() {
        return containerId;
    }

    public void setContainerId(long containerId) {
        this.containerId = containerId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventResourceTable() {
        return eventResourceTable;
    }

    public void setEventResourceTable(String eventResourceTable) {
        this.eventResourceTable = eventResourceTable;
    }

    public String getEventResourceId() {
        return eventResourceId;
    }

    public void setEventResourceId(String eventResourceId) {
        this.eventResourceId = eventResourceId;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventModel that = (EventModel) o;

        if (eventId != that.eventId) return false;
        if (containerId != that.containerId) return false;
        if (!eventType.equals(that.eventType)) return false;
        if (!eventTime.equals(that.eventTime)) return false;
        if (!eventResourceTable.equals(that.eventResourceTable)) return false;
        if (!eventResourceId.equals(that.eventResourceId)) return false;
        return eventDescription.equals(that.eventDescription);
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + (int) (containerId ^ (containerId >>> 32));
        result = 31 * result + eventType.hashCode();
        result = 31 * result + eventTime.hashCode();
        result = 31 * result + eventResourceTable.hashCode();
        result = 31 * result + eventResourceId.hashCode();
        result = 31 * result + eventDescription.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EventModel{" +
                "eventId=" + eventId +
                ", containerId=" + containerId +
                ", eventType='" + eventType + '\'' +
                ", eventTime=" + eventTime +
                ", eventResourceTable='" + eventResourceTable + '\'' +
                ", eventResourceId='" + eventResourceId + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                '}';
    }

}
