package cn.edu.njupt.zx120.controller.log;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by wallance on 4/26/17.
 */
@RestController("logController")
@RequestMapping("/api/log")
public class LogController extends BaseController {

    @Autowired
    private LogService logService;

    @RequestMapping(value = "/oper", method = RequestMethod.GET)
    public Map getAllOperLog() {
        return resultMap(true, logService.queryAllOperLog());
    }

    @RequestMapping(value = "/err", method = RequestMethod.GET)
    public Map getAllErrLog() {
        return resultMap(true, logService.queryAllErrLog());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map getAllLoginLog() {
        return resultMap(true, logService.queryAllLoginLog());
    }

}
