package cn.edu.njupt.zx120.controller.admin;

import cn.edu.njupt.zx120.controller.base.BaseController;
import cn.edu.njupt.zx120.dto.admin.ReaderDto;
import cn.edu.njupt.zx120.model.ReaderModel;
import cn.edu.njupt.zx120.service.impl.ReaderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 读卡器Controller
 * 用于管理员对读卡器的管理
 * Created by wallance on 1/17/17.
 */
@RestController
@RequestMapping("/api/admin/reader")
public class ReaderController extends BaseController {

    @Autowired
    private ReaderServiceImpl readerService;

//    @RequiresRoles(value = { "admin" })
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Map getList() {
        List<ReaderDto> readerDtos = readerService.queryAll();
        if (readerDtos.size() == 0) {
            return resultMap(false, "没有找到读卡器");
        }
        return resultMap(true, readerDtos);
    }

//    @RequiresRoles(value = { "admin" })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Map createNewReader(@RequestBody Map<String, Object> paramMap) {
        ReaderModel readerModel = new ReaderModel();

        if (paramMap.get("activeFlag") == null) {
            return resultMap(false, "ActiveFlag不可以为空");
        }
        readerModel.setActiveFlag((boolean)paramMap.get("activeFlag"));
        if (readerModel.isActiveFlag()) {
            readerModel.setActiveTime(new Date(Long.parseLong(paramMap.get("activeTime").toString())));
        } else {
            readerModel.setShutdownTime(new Date(Long.parseLong(paramMap.get("shutdownTime").toString())));
        }
        readerModel.setBindContainerId(Long.parseLong(paramMap.get("containerId").toString()));
        readerModel.setIp(paramMap.get("ip").toString());
        readerModel.setNote(paramMap.get("note").toString());
//        readerModel.setCommentId(paramMap.get("commentId").toString());
        readerService.saveReader(readerModel);
        return resultMap(true, readerModel);
    }

//    @RequiresRoles(value = { "admin" })
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Map deleteReader(@RequestParam(value = "readerId") int readerId) {

        ReaderModel readerModel = readerService.queryById(readerId);
        if (readerModel == null) {
            return resultMap(false, "没有找到到读卡器");
        }

        readerService.deleteReader(readerModel);
        return resultMap(true, "删除成功");
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Map updateParam(@RequestParam("readerId") int readerId,
                           @RequestParam("paramList") String paramList,
                           @RequestParam("paramValueList") String paramValueList) {
        String[] params = paramList.split(",");
        String[] paramValues = paramValueList.split(",");
        if (params.length != paramValues.length) {
            return resultMap(false, "参数名称和参数值不匹配");
        }
        for (int i = 0; i < params.length; i++) {
            if (params[i].equals("containerId")) {
                readerService.updateReaderContainer(readerId, Long.parseLong(paramValues[i]));
            } else if (params[i].equals("activeFlag")) {
                if (paramValues[i].equals("true")) {
                    readerService.activeReaderById(readerId);
                } else if (paramValues[i].equals("false")) {
                    readerService.shutdownReaderById(readerId);
                }
            }
        }

        return resultMap(true, readerService.queryById(readerId));
    }

}
