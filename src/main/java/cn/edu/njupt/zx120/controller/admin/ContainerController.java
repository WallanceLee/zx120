package cn.edu.njupt.zx120.controller.admin;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dao.DictionaryDao;
import cn.edu.njupt.zx120.dto.admin.ContainerTableTypeDto;
import cn.edu.njupt.zx120.model.ContainerModel;
import cn.edu.njupt.zx120.model.DictionaryParamEnum;
import cn.edu.njupt.zx120.model.ResourceTableTypeEnum;
import cn.edu.njupt.zx120.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 4/18/17.
 */
@RestController("containerAdminController")
@RequestMapping(value = "/api/admin/container")
public class ContainerController extends BaseController {

    @Autowired
    private ContainerService containerService;

    @Autowired
    private DictionaryDao dictionaryDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map getList() {
        return resultMap(true, containerService.queryAll());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map create(@RequestBody Map<String, Object> paramMap) {
        ContainerModel containerModel = new ContainerModel();
        containerModel.setContainerName(paramMap.get("containerName").toString());
        containerModel.setContainerType(Integer.parseInt(paramMap.get("containerType").toString()));
        containerModel.setResourceId(paramMap.get("resourceId").toString());
        containerModel.setResourceTable(paramMap.get("resourceTable").toString());
        containerModel.setScanPolicy(Integer.parseInt(paramMap.get("scanPolicy").toString()));
        containerService.addNewContainer(containerModel);
        return resultMap(true, containerModel);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Map delete(@RequestParam("containerId") long containerId) {
        containerService.deleteContainer(containerId);
        return resultMap(true, "删除成功");
    }

    @RequestMapping(value = "/listTypes", method = RequestMethod.GET)
    public Map listTypes() {
        return resultMap(true, dictionaryDao.queryByParam(DictionaryParamEnum.CONTAINER.getParam()));
    }

    @RequestMapping(value = "/listTableTypes", method = RequestMethod.GET)
    public Map listTableTypes() {
        List<ContainerTableTypeDto> containerTableTypeDtos = new ArrayList<>();
        ContainerTableTypeDto org = new ContainerTableTypeDto();
        org.setResourceType("机构");
        org.setTableName(ResourceTableTypeEnum.ORGANIZATION.getType());
        containerTableTypeDtos.add(org);
        ContainerTableTypeDto ambulance = new ContainerTableTypeDto();
        ambulance.setResourceType("车辆");
        ambulance.setTableName(ResourceTableTypeEnum.AMBULANCE.getType());
        containerTableTypeDtos.add(ambulance);
        return resultMap(true, containerTableTypeDtos);
    }

    @RequestMapping(value = "/listAllAmbulance", method = RequestMethod.GET)
    public Map listAllAmbulance() {
        return resultMap(true, containerService.getAllAmbulance());
    }

    @RequestMapping(value = "/listAllOrg", method = RequestMethod.GET)
    public Map listAllOrg() {
        return resultMap(true, containerService.getAllOrg());
    }

    @RequestMapping(value = "/listScanPolicy", method = RequestMethod.GET)
    public Map listScanPolicy() {
        return resultMap(true, dictionaryDao.queryByParam(DictionaryParamEnum.SCAN_POLICY.getParam()));
    }

}
