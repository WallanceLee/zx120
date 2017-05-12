package cn.edu.njupt.zx120.controller.admin;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dto.realtime.OrganizationDto;
import cn.edu.njupt.zx120.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 4/23/17.
 */
@RestController("orgAdminController")
@RequestMapping("/api/admin/org")
public class OrganizationController extends BaseController {

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map getAllOrg() {
        List<OrganizationDto> organizationDtos = organizationService.queryAll();
        if (organizationDtos.size() == 0) {
            return resultMap(false, "没有找到机构信息");
        }
        return resultMap(true, organizationDtos);
    }
}
