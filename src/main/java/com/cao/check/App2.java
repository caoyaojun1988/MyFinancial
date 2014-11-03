package com.cao.check;

import com.cao.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App2 {
    public static void main(String[] args) throws Exception {
        File file = new File("/home/caoyaojun/xpshare/20141016.xls");
        FileInputStream fileInputStream = new FileInputStream(file);
        importExcel(fileInputStream);
    }

    private static void importExcel(InputStream inputStream) throws Exception {

        List<Domain> oldList = new ArrayList<Domain>();
        List<Domain> newList = new ArrayList<Domain>();

        // 获取工作薄workbook
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream); // 读取文件流
        // 获得 sheet总数
        int sheetCount = workbook.getNumberOfSheets();
        System.out.println("ERROR sheetCount=" + sheetCount);
        // 遍历sheet
        for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
            //   System.out.println("sheetIndex =" + sheetIndex);
            List<KeyValue> sheetList = new ArrayList<KeyValue>();
            // 获得指定的sheet对象
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            // 获得本sheet的总行数
            int rowCount = sheet.getLastRowNum();

            Double oldSum = 0D;
            Double newSum = 0d;
            System.out.println("ERROR rowCount=" + rowCount);
            // 如果有数据 进入下边
            // 遍历行row ignoreRows忽略的行数。即标题行，不取
            for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
                //     System.out.println("rowIndex =" + rowIndex);
                // 获得行row对象
                HSSFRow row = sheet.getRow(rowIndex);
                // 如果此行为空，则进入下一个循环
                if (row == null) {
                    continue;
                }
                Domain oldDomain = null;
                Domain newDomain = null;

                HSSFCell cell = row.getCell(0);
                HSSFCell cell1 = row.getCell(1);
                HSSFCell cell2 = row.getCell(2);

                if (cell == null || StringUtils.isBlank(ExcelUtil.getString(cell)) || cell1 == null || StringUtils.isBlank(ExcelUtil.getString(cell1)) || cell2 == null || StringUtils.isBlank(ExcelUtil.getString(cell2))) {
                    System.out.println("ERROR ID=" + rowIndex);
                } else {
                    oldDomain = new Domain();
                    oldDomain.id = ExcelUtil.getString(cell);
                    oldDomain.name = ExcelUtil.getString(cell1);
                    oldDomain.value = ExcelUtil.getString(cell2);
                }

                HSSFCell cell3 = row.getCell(3);
                HSSFCell cell4 = row.getCell(4);
                HSSFCell cell5 = row.getCell(5);

                if (cell3 == null || StringUtils.isBlank(ExcelUtil.getString(cell3)) || cell4 == null || StringUtils.isBlank(ExcelUtil.getString(cell4)) || cell5 == null || StringUtils.isBlank(ExcelUtil.getString(cell5))) {
                    System.out.println("ERROR ID=" + rowIndex);
                } else {
                    newDomain = new Domain();
                    newDomain.id = ExcelUtil.getString(cell3);
                    newDomain.name = ExcelUtil.getString(cell4);
                    newDomain.value = ExcelUtil.getString(cell5);
                }

                if (oldDomain != null && newDomain != null && oldDomain.id.equals(newDomain.id) && oldDomain.value.equals(newDomain.value)) {

                } else {
                    if (oldDomain != null) {
                        oldList.add(oldDomain);
                    }
                    if (newDomain != null) {
                        newList.add(newDomain);
                    }
                }
                if (oldDomain != null) {
                    oldSum += Double.valueOf(oldDomain.value);
                }
                if (newDomain != null) {
                    newSum += Double.valueOf(newDomain.value);
                }
            }

            List<String> ids = new ArrayList<String>();
            for (Domain newDomain : newList) {
                for (Domain oldDomain : oldList) {
                    if (oldDomain != null && newDomain != null && oldDomain.id.equals(newDomain.id) && oldDomain.value.equals(newDomain.value)) {
                        ids.add(oldDomain.id);
                    }
                }
            }
            System.out.println("oldSum===================" + oldSum.intValue());
            System.out.println("newSum===================" + newSum.intValue());
            for (Domain domain : oldList) {
                if (!ids.contains(domain.id)) {
                    System.out.println("ID=" + domain.id + ",NAME=" + domain.name + ",VALUE=" + domain.value);
                }
            }
            System.out.println("ID===================");
            for (Domain domain : newList) {
                if (!ids.contains(domain.id)) {
                    System.out.println("ID=" + domain.id + ",NAME=" + domain.name + ",VALUE=" + domain.value);
                }
            }
        }
    }
}

class Domain {
    public String id;
    public String name;
    public String value;
}
