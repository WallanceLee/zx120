package cn.edu.njupt.zx120.controller.serviceManage;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dto.serviceManage.MemberDutyDto;
import cn.edu.njupt.zx120.dto.serviceManage.MemberDynamicDto;
import cn.edu.njupt.zx120.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 2/24/17.
 */
@RestController("memberServiceManageController")
@RequestMapping("/api/serviceManage/member")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/duty", method = RequestMethod.GET)
    public Map getDutyInfo() {
        List<MemberDutyDto> memberDutyDtos = memberService.queryAllDutyInfo();
        if (memberDutyDtos.size() == 0) {
            return resultMap(true, "没有员工在岗情况");
        }
        return resultMap(true, memberDutyDtos);
    }

    @RequestMapping(value = "/dynamic", method = RequestMethod.GET)
    public Map getDynamicInfo() {
        List<MemberDynamicDto> memberDynamicDtos = memberService.queryAllDynamicInfo();
        if (memberDynamicDtos.size() == 0) {
            return resultMap(true, "没有员工动态信息");
        }
        return resultMap(true, memberDynamicDtos);
    }

}
