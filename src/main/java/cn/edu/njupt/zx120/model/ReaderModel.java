package cn.edu.njupt.zx120.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wallance on 1/17/17.
 * 这是读卡器信息的数据表映射实体类，用于实现ORM
 */
@Entity
@Table(name = "reader_info", schema = "zx120_test", catalog = "")
public class ReaderModel implements Serializable {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "ip")
    private String ip;

    @Column(name = "bind_container_id")
    private long bindContainerId;

    @Column(name = "note")
    private String note;

    @Column(name = "activetime")
    private Date activeTime;

    @Column(name = "shutdowntime")
    private Date shutdownTime;

    @Column(name = "active_flag")
    private boolean activeFlag;

    @Column(name = "reader_commid")
    private String commentId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(java.lang.String ip) {
        this.ip = ip;
    }

    public long getBindContainerId() {
        return bindContainerId;
    }

    public void setBindContainerId(long bindContainerId) {
        this.bindContainerId = bindContainerId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(java.lang.String note) {
        this.note = note;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getShutdownTime() {
        return shutdownTime;
    }

    public void setShutdownTime(Date shutdownTime) {
        this.shutdownTime = shutdownTime;
    }

    public boolean isActiveFlag() {
        return activeFlag;
    }

    public void setActiveFlag(boolean activeFlag) {
        this.activeFlag = activeFlag;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReaderModel that = (ReaderModel) o;

        if (id != that.id) return false;
        if (bindContainerId != that.bindContainerId) return false;
        if (activeFlag != that.activeFlag) return false;
        if (!ip.equals(that.ip)) return false;
        if (!note.equals(that.note)) return false;
        if (!activeTime.equals(that.activeTime)) return false;
        if (!shutdownTime.equals(that.shutdownTime)) return false;
        return commentId.equals(that.commentId);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + ip.hashCode();
        result = 31 * result + (int) (bindContainerId ^ (bindContainerId >>> 32));
        result = 31 * result + note.hashCode();
        result = 31 * result + activeTime.hashCode();
        result = 31 * result + shutdownTime.hashCode();
        result = 31 * result + (activeFlag ? 1 : 0);
        result = 31 * result + commentId.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ReaderModel{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", bindContainerId=" + bindContainerId +
                ", note='" + note + '\'' +
                ", activeTime=" + activeTime +
                ", shutdownTime=" + shutdownTime +
                ", activeFlag=" + activeFlag +
                ", commentId='" + commentId + '\'' +
                '}';
    }

}
