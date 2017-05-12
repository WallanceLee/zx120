package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.AmbulanceDao;
import cn.edu.njupt.zx120.dao.ContainerDao;
import cn.edu.njupt.zx120.dao.OrganizationDao;
import cn.edu.njupt.zx120.dto.admin.AmbulanceDto;
import cn.edu.njupt.zx120.dto.admin.ContainerDto;
import cn.edu.njupt.zx120.dto.realtime.OrganizationDto;
import cn.edu.njupt.zx120.model.AmbulanceModel;
import cn.edu.njupt.zx120.model.ContainerModel;
import cn.edu.njupt.zx120.model.OrganizationModel;
import cn.edu.njupt.zx120.service.ContainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wallance on 3/13/17.
 */
@Service("containerService")
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    private ContainerDao containerDao;

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private AmbulanceDao ambulanceDao;

    @Override
    public List<ContainerDto> queryAll() {

        List<ContainerModel> containerModels = containerDao.queryAll();
        List<ContainerDto> containerDtos = new ArrayList<>();
        for (ContainerModel containerModel : containerModels) {
            ContainerDto containerDto = new ContainerDto();
            containerDto.setContainerId(containerModel.getContainerId());
            containerDto.setContainerName(containerModel.getContainerName());
            if (containerModel.getResourceTable().equals("sys_org_info")) {
                containerDto.setDepartment(organizationDao.queryById(containerModel.getResourceId()).getName());
            } else {
                containerDto.setDepartment(ambulanceDao.queryById(containerModel.getResourceId()).getCarBrand());
            }
            containerDto.setLastScanTime(containerModel.getLastScanTime());
            containerDtos.add(containerDto);
        }
        return containerDtos;

    }

    @Override
    public void addNewContainer(ContainerModel containerModel) {
        containerDao.addNewItem(containerModel);
    }

    @Override
    public void deleteContainer(long containerId) {
        containerDao.deleteItem(containerDao.queryByContainerId(containerId));
    }

    @Override
    public List<AmbulanceDto> getAllAmbulance() {
        List<AmbulanceModel> ambulanceModels = ambulanceDao.queryAll();
        List<AmbulanceDto> ambulanceDtos = new ArrayList<>();
        for (AmbulanceModel ambulanceModel : ambulanceModels) {
            AmbulanceDto ambulanceDto = new AmbulanceDto();
            ambulanceDto.setCarId(ambulanceModel.getCarId());
            ambulanceDto.setCarBrand(ambulanceModel.getCarBrand());
            ambulanceDtos.add(ambulanceDto);
        }
        return ambulanceDtos;
    }

    @Override
    public List<OrganizationDto> getAllOrg() {
        List<OrganizationModel> organizationModels = organizationDao.queryAll();
        List<OrganizationDto> organizationDtos = new ArrayList<>();
        for (OrganizationModel organizationModel : organizationModels) {
            OrganizationDto organizationDto = new OrganizationDto();
            organizationDto.setOrgId(organizationModel.getOrgId());
            organizationDto.setOrgName(organizationModel.getName());
            organizationDtos.add(organizationDto);
        }
        return organizationDtos;
    }
}