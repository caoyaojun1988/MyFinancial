package com.cao.input;

import com.cao.domain.CurrentMonthDetail;
import com.cao.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caoyaojun on 11/1/14.
 */
public class CurrentMonthDetailInput {

    private String                   desurl;
    private String                   orgurl;
    private List<CurrentMonthDetail> result;

    public CurrentMonthDetailInput() {

    }

    public CurrentMonthDetailInput(String orgurl, String desurl) {
        super();
        this.desurl = desurl;
        this.orgurl = orgurl;
    }

    public List<CurrentMonthDetail> importExcel() throws Exception {

        this.result = new ArrayList<CurrentMonthDetail>();
        // 获取工作薄workbook
        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(orgurl)); // 读取文件流
        // 获得 sheet总数
        int sheetCount = workbook.getNumberOfSheets();
        // 遍历sheet
        for (int sheetIndex = 0; sheetIndex < sheetCount; sheetIndex++) {
            //   System.out.println("sheetIndex =" + sheetIndex);
            // 获得指定的sheet对象
            HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
            // 获得本sheet的总行数
            int rowCount = sheet.getLastRowNum();
            // 如果没有数据
            if (rowCount <= 0) {
                return result;
            }
            // 如果有数据 进入下边
            // 遍历行row ignoreRows忽略的行数。即标题行，不取
            for (int rowIndex = 5; rowIndex <= rowCount; rowIndex++) {
                //     System.out.println("rowIndex =" + rowIndex);
                // 获得行row对象
                HSSFRow row = sheet.getRow(rowIndex);
                // 如果此行为空，则进入下一个循环
                if (row == null) {
                    continue;
                }
                HSSFCell cell = row.getCell(0);
                if (cell == null || StringUtils.isBlank(ExcelUtil.getString(cell)) || !NumberUtils.isNumber(ExcelUtil.getString(cell))) {
                    continue;
                }
                CurrentMonthDetail currentMonthDetail = new CurrentMonthDetail();
                //id
                try {
                    HSSFCell cell0 = row.getCell(0);
                    Long id = Long.valueOf(ExcelUtil.getString(cell0));
                    currentMonthDetail.setId(id);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ROW=" + rowIndex + ",culomn=" + 1 + ",value=" + row.getCell(0) + ",ERROR=" + e.getMessage());
                }
                //name
                try {
                    HSSFCell cell1 = row.getCell(1);
                    String name = (ExcelUtil.getString(cell1));
                    currentMonthDetail.setName(name);
                } catch (Exception e) {
                    throw new IllegalArgumentException("ROW=" + rowIndex + ",culomn=" + 2 + ",value=" + row.getCell(1) + ",ERROR=" + e.getMessage());
                }

                //thisBorrow
                try {
                    HSSFCell cell2 = row.getCell(2);
                    if (cell2 != null) {
                        Long thisBorrow = ((Double) (ExcelUtil.getDouble((cell2)) * 100)).longValue();
                        currentMonthDetail.setThisBorrow(thisBorrow);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("ROW=" + rowIndex + ",culomn=" + 3 + ",value=" + row.getCell(2) + ",ERROR=" + e.getMessage());
                }
                //thisLend
                try {
                    HSSFCell cell3 = row.getCell(3);
                    if (cell3 != null) {
                        Long thisLend = ((Double) (ExcelUtil.getDouble((cell3)) * 100)).longValue();
                        currentMonthDetail.setThisLend(thisLend);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("ROW=" + rowIndex + ",culomn=" + 4 + ",value=" + row.getCell(3) + ",ERROR=" + e.getMessage());
                }
                //totalBorrow
                try {
                    HSSFCell cell4 = row.getCell(4);
                    if (cell4 != null) {
                        Long totalBorrow = ((Double) (ExcelUtil.getDouble((cell4)) * 100)).longValue();
                        currentMonthDetail.setTotalBorrow(totalBorrow);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("ROW=" + rowIndex + ",culomn=" + 5 + ",value=" + row.getCell(4) + ",ERROR=" + e.getMessage());
                }
                //totalLend
                try {
                    HSSFCell cell5 = row.getCell(5);
                    if (cell5 != null) {
                        Long totalLend = ((Double) (ExcelUtil.getDouble((cell5)) * 100)).longValue();
                        currentMonthDetail.setTotalLend(totalLend);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("ROW=" + rowIndex + ",culomn=" + 6 + ",value=" + row.getCell(5) + ",ERROR=" + e.getMessage());
                }
                //endBorrow
                try {
                    HSSFCell cell6 = row.getCell(6);
                    if (cell6 != null) {
                        Long endBorrow = ((Double) (ExcelUtil.getDouble((cell6)) * 100)).longValue();
                        currentMonthDetail.setEndBorrow(endBorrow);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("ROW=" + rowIndex + ",culomn=" + 7 + ",value=" + row.getCell(6) + ",ERROR=" + e.getMessage());
                }
                //endLend
                try {
                    HSSFCell cell7 = row.getCell(7);
                    if (cell7 != null) {
                        Long endLend = ((Double) (ExcelUtil.getDouble((cell7)) * 100)).longValue();
                        currentMonthDetail.setEndLend(endLend);
                    }
                } catch (Exception e) {
                    throw new IllegalArgumentException("ROW=" + rowIndex + ",culomn=" + 8 + ",value=" + row.getCell(7) + ",ERROR=" + e.getMessage());
                }
                result.add(currentMonthDetail);
            }
        }
        return result;
    }

    public String getDesurl() {
        return desurl;
    }

    public void setDesurl(String desurl) {
        this.desurl = desurl;
    }

    public String getOrgurl() {
        return orgurl;
    }

    public void setOrgurl(String orgurl) {
        this.orgurl = orgurl;
    }

    public List<CurrentMonthDetail> getResult() {
        return result;
    }

    public void setResult(List<CurrentMonthDetail> result) {
        this.result = result;
    }
}
