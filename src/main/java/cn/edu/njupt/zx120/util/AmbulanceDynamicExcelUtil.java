package cn.edu.njupt.zx120.util;

import cn.edu.njupt.zx120.dto.serviceManage.AmbulanceDynamicDto;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by wallance on 4/27/17.
 */
public class AmbulanceDynamicExcelUtil {

    public static XSSFWorkbook createAmbulanceDynamicExcel(List<AmbulanceDynamicDto> ambulanceDynamicDtoList) {
        return export(ambulanceDynamicDtoList);
    }

    public static XSSFWorkbook export(List<AmbulanceDynamicDto> ambulanceDynamicDtoList) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("救护车出勤记录");
        XSSFRow row = sheet.createRow(0);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        createHeader(row, style);

        int columnWidth0 = "车辆id".getBytes().length;
        int columnWidth1 = "车牌号".getBytes().length;
        int columnWidth2 = "位置".getBytes().length;
        int columnWidth3 = "事件类型".getBytes().length;
        int columnWidth4 = "时间".getBytes().length;

        for (int i = 0; i < ambulanceDynamicDtoList.size(); i++) {

            row = sheet.createRow(i + 1);

            AmbulanceDynamicDto ambulanceDynamicDto = ambulanceDynamicDtoList.get(i);

            XSSFCell cell = row.createCell(0);
            cell.setCellValue(ambulanceDynamicDto.getCarId());
            cell.setCellStyle(style);
            columnWidth0 = ambulanceDynamicDto.getCarId().getBytes().length > columnWidth0 ? ambulanceDynamicDto.getCarId().getBytes().length : columnWidth0;

            cell = row.createCell(1);
            cell.setCellValue(ambulanceDynamicDto.getCarBrand());
            cell.setCellStyle(style);
            columnWidth1 = ambulanceDynamicDto.getCarBrand().getBytes().length > columnWidth1 ? ambulanceDynamicDto.getCarBrand().getBytes().length : columnWidth1;

            cell = row.createCell(2);
            cell.setCellValue(ambulanceDynamicDto.getContainerName());
            cell.setCellStyle(style);
            columnWidth2 = ambulanceDynamicDto.getContainerName().getBytes().length > columnWidth2 ? ambulanceDynamicDto.getContainerName().getBytes().length : columnWidth2;

            cell = row.createCell(3);
            cell.setCellValue(ambulanceDynamicDto.getEventType());
            cell.setCellStyle(style);
            columnWidth3 = ambulanceDynamicDto.getEventType().getBytes().length > columnWidth3 ? ambulanceDynamicDto.getEventType().getBytes().length : columnWidth3;

            cell = row.createCell(4);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            cell.setCellValue(simpleDateFormat.format(ambulanceDynamicDto.getTime()));
            cell.setCellStyle(style);
            columnWidth4 = simpleDateFormat.format(ambulanceDynamicDto.getTime()).getBytes().length > columnWidth4 ? simpleDateFormat.format(ambulanceDynamicDto.getTime()).getBytes().length : columnWidth4;

        }

        sheet.autoSizeColumn(0);
        sheet.setColumnWidth(1, columnWidth1 * 200);
        sheet.setColumnWidth(2, (columnWidth2 * 170 > 2040 ? 2040 : columnWidth2 * 170));
        sheet.setColumnWidth(3, columnWidth3 * 200);
        sheet.setColumnWidth(4, columnWidth4 * 200);
        return workbook;

    }

    private static void createHeader(XSSFRow row, XSSFCellStyle style) {

        XSSFCell cell = row.createCell(0);
        cell.setCellValue("车辆id");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("车牌号");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("位置");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("事件类型");
        cell.setCellStyle(style);

        cell = row.createCell(4);
        cell.setCellValue("时间");
        cell.setCellStyle(style);


    }

}
