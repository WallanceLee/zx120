package cn.edu.njupt.zx120.controller.serviceManage;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto;
import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceStockDto2;
import cn.edu.njupt.zx120.service.AmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
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
    public Map getDynamicInfo(@RequestParam(value = "containerId", required = false) Long containerId,
                              @RequestParam(value = "carBrand", required = false) String carBrand,
                              @RequestParam(value = "startTime", required = false)
                              @DateTimeFormat(pattern = "yyyyMMddHHmmss") Date startTime,
                              @RequestParam(value = "endTime", required = false)
                              @DateTimeFormat(pattern = "yyyyMMddHHmmss") Date endTime,
                              @RequestParam(value = "startIndex", required = false) Integer startIndex,
                              @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        System.out.println("containerId = " + containerId);
        System.out.println("carBrand = " + carBrand);
        System.out.println("startTime = " + startTime);
        System.out.println("endTime = " + endTime);
        System.out.println("startIndex = " + startIndex);
        System.out.println("pageSize = " + pageSize);
        List<AmbulanceDynamicDto> ambulanceDynamicDtos = ambulanceService.queryDynamicInfoByCondition(containerId, carBrand,
                startTime, endTime, startIndex, pageSize);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        resultMap.put("entity", ambulanceDynamicDtos);
        resultMap.put("total", ambulanceService.queryDynamicInfoTotalByCondition(containerId, carBrand,
                startTime, endTime, startIndex, pageSize));
        resultMap.put("pageSize", ambulanceDynamicDtos.size());
        return resultMap;
    }

    @RequestMapping(value = "/stock", method = RequestMethod.GET)
    public Map getStockInfo() {
        List<AmbulanceStockDto> ambulanceStockDtos = ambulanceService.queryAllStockInfo();
        return resultMap(true, ambulanceStockDtos);
    }

    @RequestMapping(value = "/stock2", method = RequestMethod.GET)
    public Map getStock2Info(@RequestParam(name = "orgId", required = false) String orgId,
                             @RequestParam(name = "startTime", required = false)
                             @DateTimeFormat(pattern = "yyyyMMddHHmmss") Date startTime,
                             @RequestParam(name = "endTime", required = false)
                             @DateTimeFormat(pattern = "yyyyMMddHHmmss") Date endTime,
                             @RequestParam(name = "startIndex", required = false) Integer startIndex,
                             @RequestParam(name = "pageSize", required = false) Integer pageSize) {
        List<AmbulanceStockDto2> ambulanceStockDto2s = ambulanceService.queryStock2InfoByCondition(orgId,
                startTime, endTime, startIndex, pageSize);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", true);
        resultMap.put("entity", ambulanceStockDto2s);
        resultMap.put("total", ambulanceService.queryStock2InfoTotalByCondition(orgId,
                startTime, endTime, startIndex, pageSize));
        resultMap.put("pageSize", ambulanceStockDto2s.size());
        return resultMap;
    }

}
