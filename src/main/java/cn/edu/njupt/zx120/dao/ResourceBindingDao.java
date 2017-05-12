package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.ResourceBindingModel;

import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
public interface ResourceBindingDao {

    List<ResourceBindingModel> queryAllResourceBindings();

    List<ResourceBindingModel> queryByResourceType(String resourceType);

    List<ResourceBindingModel> queryByResourceName(String resourceName);

    List<ResourceBindingModel> queryByResourceTable(String resourceTableName);

    ResourceBindingModel queryByResourceId(String resourceTable, String resourceIdInTable);

    void updateBindingTime(String resourceTable, String resourceIdInTable);

    void rebindResource(String resourceTable, String resourceIdInTable, String resourceTag);

    void addNewResourceBinding(ResourceBindingModel resourceBinding);

    void deleteResourceBinding(ResourceBindingModel resourceBinding);

}
