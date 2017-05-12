package cn.edu.njupt.zx120.util;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by wallance on 4/27/17.
 */
public class ExcelUtil {

    public static void downloadExcelTable(String serialNo, String type, XSSFWorkbook workbook, HttpServletResponse response) throws Exception {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            workbook.write(os);
            byte[] content = os.toByteArray();
            try (InputStream is = new ByteArrayInputStream(content);
                 BufferedInputStream bis = new BufferedInputStream(is);
                 ServletOutputStream out = response.getOutputStream();
                 BufferedOutputStream bos = new BufferedOutputStream(out)) {
                response.reset();
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setCharacterEncoding("UTF-8");
                if (serialNo != null) {
                    serialNo = URLEncoder.encode(serialNo, "UTF-8");
                    serialNo = serialNo.replaceAll("%20", " ");
                    serialNo = serialNo.replaceAll("%28", "(");
                    serialNo = serialNo.replaceAll("%29", ")");
                }

                response.setHeader("Content-Disposition", "attachment;filename=" + new String(((null != serialNo ? type + "-" + serialNo : type + "-All") + ".xlsx").getBytes(),
                        "UTF-8"));

                byte[] buff = new byte[2048];
                int bytesRead;
                while ((bytesRead = bis.read(buff, 0, buff.length)) != -1) {
                    bos.write(buff, 0, bytesRead);
                }
            }
        }
    }

}
