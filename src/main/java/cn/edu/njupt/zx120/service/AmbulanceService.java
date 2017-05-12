package cn.edu.njupt.zx120.service;

import cn.edu.njupt.zx120.dto.admin.AmbulanceBindingInfoDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto;
import cn.edu.njupt.zx120.model.AmbulanceModel;

import java.util.List;

/**
 * Created by wallance on 2/24/17.
 */
public interface AmbulanceService {

    List<AmbulanceModel> queryAll();

    List<AmbulanceModel> queryByAdminCode(int adminCode);

    List<AmbulanceModel> queryByOrgId(String orgId);

    long queryCountByOrgId(String orgId);

    long queryAvailableCountByOrgId(String orgId);

    List<AmbulanceModel> queryOutByOrgId(String orgId);

    void addNewAmbulance(AmbulanceModel ambulanceModel);

    // 注销车辆
    void setAmbulanceDisabled(String carId);

    void leaveContainer(String carId);

    void arriveContainer(String carId, int containerId, String containerType, String containerName);

    void scanResourceInCar(String carId);

    List<AmbulanceDynamicDto> queryAllDynamicInfo();

    List<AmbulanceStockDto> queryAllStockInfo();

    List<AmbulanceBindingInfoDto> queryAllBindingInfo();

    List<AmbulanceBindingInfoDto> queryAllUnboundInfo();

    List<AmbulanceBindingInfoDto> queryAllTodayBindingInfo();

}
