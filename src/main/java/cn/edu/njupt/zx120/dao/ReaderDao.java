package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.ReaderModel;

import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 1/17/17.
 */
public interface ReaderDao {

    List<ReaderModel> queryAll();

    ReaderModel queryById(int id);

    void updateIpAddress(int id, String ipAddress);

    void updateContainerId(int id, long containerId);

    void updateNotes(int id, String notes);

    void activeReader(int id);

    void setActiveTime(int id, Date activeTime);

    void shutdownReader(int id);

    void setShutdownTime(int id, Date shutdownTime);

}
