package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.dto.realtime.OrganizationDto;

import java.util.List;

/**
 * Created by wallance on 4/14/17.
 */
public interface OrganizationService {

    List<OrganizationDto> queryAll();

}
