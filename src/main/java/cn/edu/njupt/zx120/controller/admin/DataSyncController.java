package cn.edu.njupt.zx120.controller.admin;

import cn.edu.njupt.zx120.model.OrganizationModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 3/15/17.
 */
@RestController("dataSyncController")
@RequestMapping("/api/admin/sync")
public class DataSyncController {
//
//    @RequestMapping(value = "/GET_V_OUT_ORG_INFO", method = RequestMethod.GET)
//    public Map syncOrgInfo(@RequestBody JSONObject orgInfo, HttpServletResponse response) throws Exception {
//        if (orgInfo.get("isSucceeded").equals(true)) {
//            JSONArray resultData = orgInfo.getJSONArray("resultData");
//            List<OrganizationModel> organizationModels = new ArrayList<>();
//            for (int i = 0; i < resultData.size(); i++) {
//                OrganizationModel organizationModel = new OrganizationModel();
////                organizationModel.setOrgId(resultData.);
//            }
//        }
//        return null;
//    }
}
