package cn.edu.njupt.zx120.dao;

import cn.edu.njupt.zx120.model.AmbulanceModel;

import java.util.List;

/**
 * Created by wallance on 2/20/17.
 */
public interface AmbulanceDao {

    AmbulanceModel queryById(String carId);

    List<AmbulanceModel> queryByOrgId(String orgId);

    AmbulanceModel queryByCarBrand(String carBrand);

    void addNewAmbulance(AmbulanceModel ambulanceModel);

    // 注销车辆
    void setAmbulanceDisabled(String carId);

    // 查找该地区的救护车
    List<AmbulanceModel> queryByAdminCode(int adminCode);

    // 查询所有救护车
    List<AmbulanceModel> queryAll();

    // 设置创建时间
    void setCreateTime(String carId);

    // 更新最近修改时间
    void updateLastModifiedTime(String carId);

    // 更新所属机构id
    void updateOrdId(String carId, String orgId);


}
