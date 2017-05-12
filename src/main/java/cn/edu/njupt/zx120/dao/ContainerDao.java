package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.ContainerModel;

import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
public interface ContainerDao {

    List<ContainerModel> queryAll();

    ContainerModel queryByContainerId(long containerId);

    ContainerModel queryByResourceId(String resourceId, String resourceTable);

    void addNewItem(ContainerModel containerModel);

    void deleteItem(ContainerModel containerModel);

    void updateResourceContainer(String containerId, String resourceTable, String resourceId);

    void updateContainerLastScanTime(String containerId);

    void updateResourceLastScanTime(String containerId, String resourceTable, String resourceId);

}
