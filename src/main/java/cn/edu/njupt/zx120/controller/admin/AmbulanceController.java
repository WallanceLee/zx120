package cn.edu.njupt.zx120.controller.admin;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dto.admin.AmbulanceBindingInfoDto;
import cn.edu.njupt.zx120.service.AmbulanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 4/21/17.
 */
@RestController("ambulanceAdminController")
public class AmbulanceController extends BaseController {

    @Autowired
    private AmbulanceService ambulanceService;

    @RequestMapping(value = "/api/admin/ambulance", method = RequestMethod.GET)
    public Map getAmbulanceBindingInfo(@RequestParam("mode") String mode) {

        List<AmbulanceBindingInfoDto> ambulanceBindingInfoDtos = new ArrayList<>();

        if (mode.equals("bound")) {
            ambulanceBindingInfoDtos = ambulanceService.queryAllBindingInfo();
        } else if (mode.equals("unbound")) {
            ambulanceBindingInfoDtos = ambulanceService.queryAllUnboundInfo();
        } else if (mode.equals("today")) {
            ambulanceBindingInfoDtos = ambulanceService.queryAllTodayBindingInfo();
        }

        if (ambulanceBindingInfoDtos == null) {
            return resultMap(false, "参数错误");
        }

        if (ambulanceBindingInfoDtos.size() == 0) {
            if (mode.equals("bound")) {
                return resultMap(false, "没有查询到已绑定资源信息");
            } else if (mode.equals("unbound")) {
                return resultMap(false, "没有查询到未绑定资源信息");
            } else if (mode.equals("today")) {
                return resultMap(false, "未查询到今日绑定信息");
            }
        }

        return resultMap(true, ambulanceBindingInfoDtos);
    }

}
