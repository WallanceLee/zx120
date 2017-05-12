package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.model.ResourceBindingModel;

import java.util.List;

/**
 * Created by wallance on 3/3/17.
 */
public interface ResourceBindingService {

    List<ResourceBindingModel> queryAllResourceBindings();

    List<ResourceBindingModel> queryByResourceType(String resourceType);

    List<ResourceBindingModel> queryByResourceName(String resourceName);

    List<ResourceBindingModel> queryByResourceTable(String resourceTableName);

    ResourceBindingModel queryByResourceId(String resourceTable, String resourceIdInTable);

    void updateBindingTime(String resourceTable, String resourceIdInTable);

    void rebindResource(String resourceType, String resourceId, String tag);

    void addNewResourceBinding(ResourceBindingModel resourceBinding);

    void deleteResourceBinding(ResourceBindingModel resourceBinding);

    void batchBind(String resourceType, String tagList, String resourceIdList);

    void manualBind(String resourceType, String resourceId, String tag);

}
