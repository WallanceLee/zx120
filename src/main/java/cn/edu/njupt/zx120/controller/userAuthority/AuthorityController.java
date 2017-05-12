package cn.edu.njupt.zx120.controller.userAuthority;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dao.AuthoritiesDao;
import cn.edu.njupt.zx120.model.AuthorityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by wallance on 4/25/17.
 */
@RestController("authorityController")
@RequestMapping("/api/authority")
public class AuthorityController extends BaseController {

    @Autowired
    private AuthoritiesDao authoritiesDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map getAllAuthority() {
        return resultMap(true, authoritiesDao.queryAll());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map createNewAuthority(@RequestBody Map<String, String> paramMap) {
        AuthorityModel authorityModel = new AuthorityModel();
        authorityModel.setType(paramMap.get("type"));
        authorityModel.setDepartment(paramMap.get("department"));
        authorityModel.setFunction(paramMap.get("_function"));
        authorityModel.setFunction(paramMap.get("param"));
        authoritiesDao.addNewAuthority(authorityModel);
        return resultMap(true, "添加权限成功");
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Map deleteAuthority(@RequestParam("type") String type) {
        authoritiesDao.deleteAuthorityByType(type);
        return resultMap(true, "删除权限成功");
    }

}
