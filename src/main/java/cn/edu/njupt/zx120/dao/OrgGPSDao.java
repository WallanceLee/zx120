package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.OrgGPSModel;

import java.util.List;

/**
 * Created by wallance on 4/14/17.
 */
public interface OrgGPSDao {

    OrgGPSModel queryByOrgId(String orgId);

    void saveNewOrgGPS(OrgGPSModel orgGPSModel);

    void updateOrgGPS(String orgId, String longtitude, String latitude);

}
