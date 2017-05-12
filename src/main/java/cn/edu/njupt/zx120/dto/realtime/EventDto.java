package cn.edu.njupt.zx120.dto.realtime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 4/14/17.
 */
public class EventDto {

    private int eventId;

    private List<String> memberNames;

    private List<String> ambulanceBrands;

    private String eventType;

    private Date eventTime;

    private String containerName;

    public EventDto() {
        eventId = -1;
        memberNames = new ArrayList<>();
        ambulanceBrands = new ArrayList<>();
        eventType = "";
        eventTime = new Date();
        containerName = "";
    }

    public EventDto(Date eventTime, String containerName) {
        this.eventTime = eventTime;
        this.containerName = containerName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public List<String> getMemberNames() {
        return memberNames;
    }

    public void setMemberNames(List<String> memberNames) {
        this.memberNames = memberNames;
    }

    public List<String> getAmbulanceBrands() {
        return ambulanceBrands;
    }

    public void setAmbulanceBrands(List<String> ambulanceBrands) {
        this.ambulanceBrands = ambulanceBrands;
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

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventDto eventDto = (EventDto) o;

        if (eventId != eventDto.eventId) return false;
        if (!memberNames.equals(eventDto.memberNames)) return false;
        if (!ambulanceBrands.equals(eventDto.ambulanceBrands)) return false;
        if (!eventType.equals(eventDto.eventType)) return false;
        if (!eventTime.equals(eventDto.eventTime)) return false;
        return containerName.equals(eventDto.containerName);
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + memberNames.hashCode();
        result = 31 * result + ambulanceBrands.hashCode();
        result = 31 * result + eventType.hashCode();
        result = 31 * result + eventTime.hashCode();
        result = 31 * result + containerName.hashCode();
        return result;
    }

    @Override
    public String toString() {
//        return "EventDto{" +
//                "eventId=" + eventId +
//                ", memberNames=" + memberNames +
//                ", ambulanceBrands=" + ambulanceBrands +
//                ", eventType='" + eventType + '\'' +
//                ", eventTime=" + eventTime +
//                ", containerName='" + containerName + '\'' +
//                '}';
        String result = "" + eventId + ". ";
        if (memberNames.size() > 0) {
            for (int i = 0; i < memberNames.size() - 1; i++) {
                result = result + memberNames.get(i) + "、";
            }
            result += memberNames.get(memberNames.size() - 1);
            result += ",";
        }
        if (ambulanceBrands.size() > 0) {
            for (int i = 0; i < ambulanceBrands.size() - 1; i++) {
                result = result + ambulanceBrands.get(i) + "、";
            }
            result += ambulanceBrands.get(ambulanceBrands.size() - 1);
        }
        if (memberNames.size() == 0 && ambulanceBrands.size() == 0) {
            return "";
        }
        result += "于";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        result += simpleDateFormat.format(eventTime);
        if (eventType.equals("check_in")) {
            result += "进入" + containerName + ".";
        } else if (eventType.equals("check_out")) {
            result += "从" + containerName + "离开.";
        }
        return result;
    }

}
