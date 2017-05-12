package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.AmbulanceDao;
import cn.edu.njupt.zx120.dao.DeviceDao;
import cn.edu.njupt.zx120.dao.MemberDao;
import cn.edu.njupt.zx120.dao.ResourceBindingDao;
import cn.edu.njupt.zx120.model.ResourceBindingModel;
import cn.edu.njupt.zx120.model.ResourceTableTypeEnum;
import cn.edu.njupt.zx120.model.ResourceTypeEnum;
import cn.edu.njupt.zx120.service.ResourceBindingService;
import jdk.management.resource.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wallance on 3/3/17.
 */
@Service("resourceBindingService")
public class ResourceBingdingServiceImpl implements ResourceBindingService {

    @Autowired
    private ResourceBindingDao resourceBindingDao;

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private DeviceDao deviceDao;

    @Autowired
    private AmbulanceDao ambulanceDao;

    private String getResourceTableNameFromResourceType(String resourceType) {
        if (resourceType.equals(ResourceTypeEnum.AMBULANCE.getType())) {
            return ResourceTableTypeEnum.AMBULANCE.getType();
        }
        else if (resourceType.equals(ResourceTypeEnum.MEMBER.getType())) {
            return ResourceTableTypeEnum.MEMBER.getType();
        }

        return "参数错误";
    }

    @Override
    public List<ResourceBindingModel> queryAllResourceBindings() {
        return resourceBindingDao.queryAllResourceBindings();
    }

    @Override
    public List<ResourceBindingModel> queryByResourceType(String resourceType) {
        return resourceBindingDao.queryByResourceType(resourceType);
    }

    @Override
    public List<ResourceBindingModel> queryByResourceName(String resourceName) {
        return resourceBindingDao.queryByResourceName(resourceName);
    }

    @Override
    public List<ResourceBindingModel> queryByResourceTable(String resourceTableName) {
        return resourceBindingDao.queryByResourceTable(resourceTableName);
    }

    @Override
    public ResourceBindingModel queryByResourceId(String resourceTable, String resourceIdInTable) {
        return resourceBindingDao.queryByResourceId(resourceTable, resourceIdInTable);
    }

    @Override
    public void updateBindingTime(String resourceTable, String resourceIdInTable) {
        resourceBindingDao.updateBindingTime(resourceTable, resourceIdInTable);
    }

    @Override
    public void rebindResource(String resourceType, String resourceId, String tag) {
        resourceBindingDao.rebindResource(resourceType, resourceId, tag);
    }

    @Override
    public void addNewResourceBinding(ResourceBindingModel resourceBinding) {
        resourceBindingDao.addNewResourceBinding(resourceBinding);
    }

    @Override
    public void deleteResourceBinding(ResourceBindingModel resourceBinding) {
        resourceBindingDao.deleteResourceBinding(resourceBinding);
    }

    @Override
    public void batchBind(String resourceType, String tagList, String resourceIdList) {
        // 得到所有要绑定的标签
        String[] tags = tagList.split(",");
        // 得到所有要绑定的资源id
        String[] resourceIds = resourceIdList.split(",");
        // 检查两者数量是否一致
        if (tags.length == resourceIds.length) {
            String resourceTableName = getResourceTableNameFromResourceType(resourceType);
            if (resourceTableName.equals("参数错误")) {
                // 返回参数错误
            }
            for (int i = 0; i < resourceIds.length; i++) {
                ResourceBindingModel resourceBindingModel = resourceBindingDao.queryByResourceId(resourceTableName, resourceIds[i]);
                if (resourceBindingModel == null) {
                    ResourceBindingModel newResourceBindingModel = new ResourceBindingModel();
                    newResourceBindingModel.setTag(tags[i]);
                    newResourceBindingModel.setResourceType(resourceType);
                    newResourceBindingModel.setResourceTableName(resourceTableName);
                    if (resourceType.equals(ResourceTypeEnum.AMBULANCE.getType())) {
                        newResourceBindingModel.setResourceName(ambulanceDao.queryById(resourceIds[i]).getCarBrand());
                    } else if (resourceType.equals(ResourceTypeEnum.MEMBER.getType())) {
                        newResourceBindingModel.setResourceName(memberDao.queryById(resourceIds[i]).getName());
                    } // else {} 上面已判断过，这里直接省略
                    newResourceBindingModel.setResourceId(resourceIds[i]);
                    newResourceBindingModel.setBindTime(new Date());
                    addNewResourceBinding(newResourceBindingModel);
                } else {
                    rebindResource(resourceTableName, resourceIds[i], tags[i]);
                    updateBindingTime(resourceTableName, resourceIds[i]);
                }
            }
        } else {
            // 返回资源与tag数量不匹配
        }
    }

    @Override
    public void manualBind(String resourceType, String resourceId, String tag) {
        String resourceTableName = getResourceTableNameFromResourceType(resourceType);
        if (resourceTableName.equals("参数错误")) {
            // 返回参数错误
        }

        ResourceBindingModel resourceBindingModel = resourceBindingDao.queryByResourceId(resourceTableName, resourceId);

        if (resourceBindingModel == null) {
            ResourceBindingModel newResourceBindingModel = new ResourceBindingModel();
            newResourceBindingModel.setTag(tag);
            newResourceBindingModel.setResourceType(resourceType);
            newResourceBindingModel.setResourceTableName(resourceTableName);
            if (resourceType.equals(ResourceTypeEnum.AMBULANCE.getType())) {
                newResourceBindingModel.setResourceName(ambulanceDao.queryById(resourceId).getName());
            } else if (resourceType.equals(ResourceTypeEnum.MEMBER.getType())) {
                newResourceBindingModel.setResourceName(memberDao.queryById(resourceId).getName());
            } // else {} 上面已判断过，这里直接省略
            newResourceBindingModel.setResourceId(resourceId);
            newResourceBindingModel.setBindTime(new Date());
            addNewResourceBinding(newResourceBindingModel);
        } else {
            rebindResource(resourceTableName, resourceId, tag);
            updateBindingTime(resourceTableName, resourceId);
        }
    }
}
