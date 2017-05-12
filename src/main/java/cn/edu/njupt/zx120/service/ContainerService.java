package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.dto.admin.AmbulanceDto;
import cn.edu.njupt.zx120.dto.admin.ContainerDto;
import cn.edu.njupt.zx120.dto.realtime.OrganizationDto;
import cn.edu.njupt.zx120.model.ContainerModel;

import java.util.List;

/**
 * Created by wallance on 3/13/17.
 */
public interface ContainerService {

    List<ContainerDto> queryAll();

    void addNewContainer(ContainerModel containerModel);

    void deleteContainer(long containerId);

    List<AmbulanceDto> getAllAmbulance();

    List<OrganizationDto> getAllOrg();

}
