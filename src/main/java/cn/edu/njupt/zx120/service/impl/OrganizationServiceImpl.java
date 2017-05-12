package cn.edu.njupt.zx120.service.impl;

import cn.edu.njupt.zx120.dao.OrgGPSDao;
import cn.edu.njupt.zx120.dao.OrganizationDao;
import cn.edu.njupt.zx120.dto.realtime.OrganizationDto;
import cn.edu.njupt.zx120.dto.realtime.OrganizationPositionDto;
import cn.edu.njupt.zx120.model.OrgGPSModel;
import cn.edu.njupt.zx120.model.OrganizationModel;
import cn.edu.njupt.zx120.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wallance on 4/14/17.
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private OrgGPSDao orgGPSDao;

    @Override
    public List<OrganizationDto> queryAll() {
        List<OrganizationModel> organizationModels = organizationDao.queryAll();
        List<OrganizationDto> organizationDtos = new ArrayList<>();
        for (OrganizationModel organizationModel : organizationModels) {
            OrganizationDto organizationDto = new OrganizationDto();
            organizationDto.setOrgId(organizationModel.getOrgId());
            organizationDto.setOrgName(organizationModel.getName());
            // 从数据库表里查询是否存在该机构的GPS缓存
            // 如果存在，直接返回
            // 如果不存在，返回空
            OrgGPSModel orgGPSModel = orgGPSDao.queryByOrgId(organizationModel.getOrgId());
            OrganizationPositionDto organizationPositionDto = new OrganizationPositionDto();
            if (orgGPSModel == null) {
                organizationPositionDto.setLongitude("");
                organizationPositionDto.setLatitude("");
            } else {
                organizationPositionDto.setLongitude(orgGPSModel.getLongitude());
                organizationPositionDto.setLatitude(orgGPSModel.getLatitude());
            }
            organizationDto.setPosition(organizationPositionDto);

            organizationDtos.add(organizationDto);
        }
        return organizationDtos;
    }

}
