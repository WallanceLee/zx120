package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.OrganizationModel;

import java.util.List;

/**
 * Created by wallance on 1/22/17.
 */
public interface OrganizationDao {

    List<OrganizationModel> queryAll();

    OrganizationModel queryById(String id);

    OrganizationModel queryByName(String name);

    List<OrganizationModel> queryByType(int type);

    void saveOrganization(OrganizationModel organizationModel);

}
