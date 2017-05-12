package cn.edu.njupt.zx120.controller.statistics;

import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto;
import cn.edu.njupt.zx120.service.AmbulanceService;
import cn.edu.njupt.zx120.util.AmbulanceDynamicExcelUtil;
import cn.edu.njupt.zx120.util.ExcelUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by wallance on 4/27/17.
 */
@RestController("statisticsController")
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private AmbulanceService ambulanceService;

    @RequestMapping(value = "/memberDuty", method = RequestMethod.GET)
    public void generateDutyTable(//@RequestParam("type") String type,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {

    }

    @RequestMapping(value = "/ambulanceDynamic", method = RequestMethod.GET)
    public void generateAmbulanceDynamicTable(@RequestParam("type") String type,
                                              HttpServletRequest request,
                                              HttpServletResponse response) throws Exception {
        XSSFWorkbook workbook = null;
        try {
            List<AmbulanceDynamicDto> ambulanceDynamicDtoList = ambulanceService.queryAllDynamicInfo();
            workbook = AmbulanceDynamicExcelUtil.createAmbulanceDynamicExcel(ambulanceDynamicDtoList);
            ExcelUtil.downloadExcelTable(null, null, workbook, response);
        } catch (Exception e) {
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print("创建excel文件失败");
            throw new RuntimeException("创建excel文件失败", e);
        }
    }

}
