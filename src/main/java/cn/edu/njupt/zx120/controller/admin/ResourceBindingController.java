package cn.edu.njupt.zx120.controller.admin;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.model.ResourceTableTypeEnum;
import cn.edu.njupt.zx120.model.ResourceTypeEnum;
import cn.edu.njupt.zx120.service.ResourceBindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 3/19/17.
 */
@RestController("resourceBindingController")
@RequestMapping("/api/admin/rfid")
public class ResourceBindingController extends BaseController {

    @Autowired
    private ResourceBindingService resourceBindingService;

    @RequestMapping(value = "/getResourceTypeList", method = RequestMethod.GET)
    public Map getResourceTypeList() {
        List<String> resourceTypes = new ArrayList<>();
        resourceTypes.add(ResourceTypeEnum.AMBULANCE.getType());
        resourceTypes.add(ResourceTypeEnum.MEMBER.getType());
        return resultMap(true, resourceTypes);
    }

    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public Map bindResources(@RequestParam(value = "resourceType") String resourceType,
                             @RequestParam(value = "mode") String mode,
                             @RequestParam(value = "tagList", required = false) String tagList,
                             @RequestParam(value = "resourceIdList", required = false) String resourceIdList,
                             @RequestParam(value = "tag", required = false) String tag,
                             @RequestParam(value = "resourceId", required = false) String resourceId) {
        String resourceTableName = null;
        if (resourceType.equals(ResourceTypeEnum.AMBULANCE.getType())) {
            resourceTableName =  ResourceTableTypeEnum.AMBULANCE.getType();
        }
        else if (resourceType.equals(ResourceTypeEnum.MEMBER.getType())) {
            resourceTableName = ResourceTableTypeEnum.MEMBER.getType();
        }

        if (mode.equals("batch")) {
            if (tagList != null && resourceIdList != null) {
                resourceBindingService.batchBind(resourceType, tagList, resourceIdList);
            } else {

            }
        } else if (mode.equals("manual")) {
            if (tag != null && resourceId != null) {
                resourceBindingService.manualBind(resourceType, resourceId, tag);
            } else {

            }
        } else if (mode.equals("rebind")) {
            if (tag != null && resourceId != null) {

                resourceBindingService.rebindResource(resourceTableName, resourceId, tag);

            } else {

            }
        } else if (mode.equals("cancel")) {
            if (resourceId != null) {
                resourceBindingService.deleteResourceBinding(resourceBindingService.queryByResourceId(resourceTableName, resourceId));
            }
        }

        return null;
    }

}
