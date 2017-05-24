package cn.edu.njupt.zx120.dto.serviceManage;

import java.util.Date;

/**
 * Created by wallance on 17-5-18.
 */
public class AmbulanceStockDto2 {

    private String orgId;

    private String department;

    private long remainNumber;

    private long outNumber;

    private long visitedNumber;

    private Date time;

    public AmbulanceStockDto2(String orgId, String department, long remainNumber, long visitedNumber, long outNumber, Date time) {
        this.orgId = orgId;
        this.department = department;
        this.remainNumber = remainNumber;
        this.outNumber = outNumber;
        this.visitedNumber = visitedNumber;
        this.time = time;
    }

    public AmbulanceStockDto2() {
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public long getRemainNumber() {
        return remainNumber;
    }

    public void setRemainNumber(long remainNumber) {
        this.remainNumber = remainNumber;
    }

    public long getOutNumber() {
        return outNumber;
    }

    public void setOutNumber(long outNumber) {
        this.outNumber = outNumber;
    }

    public long getVisitedNumber() {
        return visitedNumber;
    }

    public void setVisitedNumber(long visitedNumber) {
        this.visitedNumber = visitedNumber;
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

        AmbulanceStockDto2 that = (AmbulanceStockDto2) o;

        if (remainNumber != that.remainNumber) return false;
        if (outNumber != that.outNumber) return false;
        if (visitedNumber != that.visitedNumber) return false;
        if (!orgId.equals(that.orgId)) return false;
        if (!department.equals(that.department)) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result = orgId.hashCode();
        result = 31 * result + department.hashCode();
        result = 31 * result + (int) (remainNumber ^ (remainNumber >>> 32));
        result = 31 * result + (int) (outNumber ^ (outNumber >>> 32));
        result = 31 * result + (int) (visitedNumber ^ (visitedNumber >>> 32));
        result = 31 * result + time.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AmbulanceStockDto2{" +
                "orgId='" + orgId + '\'' +
                ", department='" + department + '\'' +
                ", remainNumber=" + remainNumber +
                ", outNumber=" + outNumber +
                ", visitedNumber=" + visitedNumber +
                ", time=" + time +
                '}';
    }

}
