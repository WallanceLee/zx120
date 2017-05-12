package cn.edu.njupt.zx120.controller.admin;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dto.admin.MemberBindingInfoDto;
import cn.edu.njupt.zx120.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by wallance on 4/18/17.
 */
@RestController("memberAdminController")
public class MemberController extends BaseController {

    @Autowired
    private MemberService memberService;

    @RequestMapping(value = "/api/admin/member", method = RequestMethod.GET)
    public Map getMemberBindingInfo(@RequestParam(value = "mode") String mode) {

        List<MemberBindingInfoDto> memberBindingInfoDtos = null;

        if (mode.equals("bound")) {
            memberBindingInfoDtos = memberService.queryAllBindingInfo();
        } else if (mode.equals("unbound")) {
            memberBindingInfoDtos = memberService.queryAllUnboundInfo();
        } else if (mode.equals("today")) {
            memberBindingInfoDtos = memberService.queryAllTodayBindingInfo();
        }

        if (memberBindingInfoDtos == null) {
            return resultMap(false, "参数错误");
        }

        if (memberBindingInfoDtos.size() == 0) {
            if (mode.equals("bound"))
                return resultMap(false, "没有查询到已绑定资源信息");
            else if (mode.equals("unbound"))
                return resultMap(false, "没有查询到未绑定资源信息");
            else if (mode.equals("today"))
                return resultMap(false, "未查询到今日绑定信息");
        }

        return resultMap(true, memberBindingInfoDtos);
    }
}
