package cn.edu.njupt.zx120.controller.serviceManage;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto;
import cn.edu.njupt.zx120.service.AmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 3/14/17.
 */
@RestController("ambulanceController")
@RequestMapping("/api/serviceManage/ambulance")
public class AmbulanceController extends BaseController {

    @Autowired
    private AmbulanceService ambulanceService;

    @RequestMapping(value = "/dynamic", method = RequestMethod.GET)
    public Map getDynamicInfo() {
        List<AmbulanceDynamicDto> ambulanceDynamicDtos = ambulanceService.queryAllDynamicInfo();
        return resultMap(true, ambulanceDynamicDtos);
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public Map getStockInfo() {
        List<AmbulanceStockDto> ambulanceStockDtos = ambulanceService.queryAllStockInfo();
        return resultMap(true, ambulanceStockDtos);
    }

}
