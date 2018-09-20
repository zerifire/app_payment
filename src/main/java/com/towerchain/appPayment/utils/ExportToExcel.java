package com.towerchain.appPayment.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by TowerChain_T01 on 2018/9/18.
 */
public class ExportToExcel {
    private static Logger log = LoggerFactory.getLogger(ExportToExcel.class);

    public static String generateExcel(String paramStr, String title, String secTitle) {
        System.out.println(paramStr);
        //以 secTitle 分割成两个字符串
        String[] allData = paramStr.split(secTitle);
        //获取列的名称
        String[] columnList = title.split(",");
//        将内容以`分割成单独的数据
        String[] data = allData[0].replace(title, "").replace(",", "").split("`");
//        获取第二条字符串的列的名称
        String[] secData = allData[allData.length - 1].split("`");
//        将内容以，分割成单独的数据
        String[] secTitleData = secTitle.split(",");
//        总的list集合
        List<List<String>> list = new LinkedList<>();
//        每一行数据存入一天list
        List<String> list1 = null;
        int columnSize = columnList.length;
        for (int i = 0; i < data.length / columnSize; i++) {
            list1 = new LinkedList<>();
            for (int j = 0; j < columnSize; j++) {
                list1.add(data[i * columnSize + j + 1]);
            }
            list.add(list1);
        }
        try {
            if (list.size() > 0) {
                export2XlSX(columnList, list, secTitleData, secData);
            } else {
                return "no_bill";
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return "ERROR";
        }
        return "SUCCESS";
    }


    /**
     * 生成 .xslx 表格
     *
     * @param columnList
     * @param list
     * @return
     * @throws IOException
     */
    public static byte[] export2XlSX(String[] columnList, List<List<String>> list, String[] secTitle, String[] secData) throws IOException {
        // 创建excel工作薄
        @SuppressWarnings("resource")
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 创建一个工作表sheet
        Sheet sheet = workbook.createSheet();
        byte[] bs = null;
        // 创建第一行
        Row row = sheet.createRow(0);
        Cell cell = null;
        // 插入第一行数据
        for (int i = 0; i < columnList.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(columnList[i]);
        }
        //对应每行的数据
        for (int i = 0; i < list.size(); i++) {
            List<String> list1 = list.get(i);
            Row nextRow = sheet.createRow(i + 1);
            for (int k = 0; k < list1.size(); k++) {
                Cell cell1 = nextRow.createCell(k);
                cell1.setCellValue(list1.get(k));
            }
        }


//        将统计的一列加入到最后一行
        Row nextRow1 = sheet.createRow(list.size() + 1);
        for (int i = 0; i < secTitle.length; i++) {
            Cell cell1 = nextRow1.createCell(i);
            cell1.setCellValue(secTitle[i]);
        }
//        统计对应的值
        Row nextRow2 = sheet.createRow(list.size() + 2);
        for (int i = 0; i < secTitle.length; i++) {
            Cell cell1 = nextRow2.createCell(i);
            cell1.setCellValue(secData[i + 1]);
        }

        String fileName = getXLSXStr().toString();
        String name = "C:/app_pay/excel/" + fileName + ".xlsx";
        File file = new File(name);
        try {
            file.createNewFile();
            // 创建输出流
            FileOutputStream stream = FileUtils.openOutputStream(file);
            // 将拼好的Excel写入到文件流
            workbook.write(stream);
            // 关闭输出流
            stream.close();
            bs = FileUtils.readFileToByteArray(file);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return bs;
    }

    /**
     * xlsx 命名字符串
     *
     * @return
     */

    private static StringBuffer getXLSXStr() {
        StringBuffer str = new StringBuffer();
        try {
            Calendar a = Calendar.getInstance();
            int year = a.get(Calendar.YEAR);
            int month = a.get(Calendar.MONTH) + 1;
            int day = a.get(Calendar.DATE);
            int hour = a.get(Calendar.HOUR);
            int min = a.get(Calendar.MINUTE);
            int sec = a.get(Calendar.SECOND);
            str.append((year + "-" + month + "-" + day + "-" + hour + "-" + min + "-" + sec + "app支付"));
            return str;
        } catch (Exception e) {
            return str;
        }
    }


    /**
     * 时间转换
     *
     * @param date_str
     * @return
     */
    private static Date dateTimeString2Date(String date_str) {
        try {
            Calendar cal = Calendar.getInstance();
            java.sql.Timestamp timestampnow = new java.sql.Timestamp(cal.getTimeInMillis());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ParsePosition pos = new ParsePosition(0);
            java.util.Date current = formatter.parse(date_str, pos);
            timestampnow = new java.sql.Timestamp(current.getTime());
            return timestampnow;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * App支付 时间转换时间戳 时间格式为yyyyMMddHHmm
     *
     * @param str
     * @return
     * @throws ParseException
     */
    private static long getTimeMillis(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        return sdf.parse(str).getTime();
    }

}
