package cn.edu.njupt.zx120.controller.admin;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dao.AmbulanceDao;
import cn.edu.njupt.zx120.dao.MemberDao;
import cn.edu.njupt.zx120.dao.OrganizationDao;
import cn.edu.njupt.zx120.model.AmbulanceModel;
import cn.edu.njupt.zx120.model.MemberModel;
import cn.edu.njupt.zx120.model.OrganizationModel;
import cn.edu.njupt.zx120.model.ResourceTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 4/24/17.
 */
@RestController("resourceInfoController")
@RequestMapping("/api/admin/resourceInfo")
public class ResourceInfoController extends BaseController {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private AmbulanceDao ambulanceDao;

    @Autowired
    private OrganizationDao organizationDao;

    @RequestMapping(value = "/resourceTypes", method = RequestMethod.GET)
    public Map getResourceTypes() {
        List<String> resourceTypes = new ArrayList<>();
        resourceTypes.add(ResourceTypeEnum.ORGANIZATION.getType());
        resourceTypes.add(ResourceTypeEnum.AMBULANCE.getType());
        resourceTypes.add(ResourceTypeEnum.MEMBER.getType());
        return resultMap(true, resourceTypes);
    }

    @RequestMapping(value = "/member", method = RequestMethod.GET)
    public Map getAllMember() {
        List<MemberModel> memberModels = memberDao.queryAll();
        return resultMap(true, memberModels);
    }

    @RequestMapping(value = "/ambulance", method = RequestMethod.GET)
    public Map getAllAmbulance() {
        List<AmbulanceModel> ambulanceModels = ambulanceDao.queryAll();
        return resultMap(true, ambulanceModels);
    }

    @RequestMapping(value = "/organization", method = RequestMethod.GET)
    public Map getAllOrganization() {
        List<OrganizationModel> organizationModels = organizationDao.queryAll();
        return resultMap(true, organizationModels);
    }

}
