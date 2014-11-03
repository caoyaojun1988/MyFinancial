package com.cao.output;

import com.cao.domain.CurrentMonthDetail;
import com.cao.main.UI;
import com.cao.util.ExcelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Map;

/**
 * Created by caoyaojun on 11/1/14.
 */
public class ZhiChuJinDuOutput extends AbstractOutput {

    private static final String exclued = "减：";
    private Map<String, CurrentMonthDetail> values;

    public ZhiChuJinDuOutput() {
        super();
    }


    public HSSFWorkbook CreateExcel(HSSFWorkbook workbook) {
        try {
            // 获得 sheet总数
            int sheetCount = workbook.getNumberOfSheets();
            // 遍历sheet
            for (int sheetIndex = 3; sheetIndex < 4; sheetIndex++) {
                //   System.out.println("sheetIndex =" + sheetIndex);
                // 获得指定的sheet对象
                HSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                // 获得本sheet的总行数
                int rowCount = sheet.getLastRowNum();
                // 如果没有数据
                if (rowCount <= 0) {
                    return workbook;
                }
                // 如果有数据 进入下边
                // 遍历行row ignoreRows忽略的行数。即标题行，不取
                for (int rowIndex = 4; rowIndex <= rowCount; rowIndex++) {
                    HSSFRow row = sheet.getRow(rowIndex);
                    // 如果此行为空，则进入下一个循环
                    if (row == null) {
                        continue;
                    }

                    try {
                        HSSFCell cell1 = row.getCell(1);
                        String cell1Value = ExcelUtil.getString(cell1);
                        if (StringUtils.isNotBlank(cell1Value)) {

                            HSSFCell cell2 = row.getCell(2);
                            setCellValue(cell1Value, cell2);

                            HSSFCell cell3 = row.getCell(3);
                            setCellValue(cell1Value, cell3);

                            HSSFCell cell4 = row.getCell(4);
                            setCellValue(cell1Value, cell4);
                            HSSFCell cell5 = row.getCell(5);
                            setCellValue(cell1Value, cell5);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new IllegalArgumentException("ROW=" + rowIndex + ",culomn=" + 1 + ",value=" + row.getCell(0) + ",ERROR=" + e.getMessage());
                    }
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            UI.showException("明细表ERROR=" + e.getMessage() + "");
        }
        return workbook;
    }

    protected void setCellValue(String cell0Value, HSSFCell cell1) {
        if (cell1 != null) {
            String mapper = ExcelUtil.getString(cell1);
            if (StringUtils.isNotBlank(mapper) && NumberUtils.isNumber(mapper)) {
                int index = Double.valueOf(mapper).intValue();

                String replaceAll = cell0Value.replaceAll("\\(?\\（?\\d*\\)?\\）?", "");
                String replace = StringUtils.replace(replaceAll, "、", "");
                String value = StringUtils.replace(replace, " ", "");

                CurrentMonthDetail currentMonthDetail = getValues().get(value);
                if (currentMonthDetail != null && currentMonthDetail.getValueById(index) != null && currentMonthDetail.getValueById(index) != 0) {
                    cell1.setCellValue(processValue(currentMonthDetail.getValueById(index)));
                    return;
                }
                cell1.setCellValue("");
            }
        }
    }


    public Map<String, CurrentMonthDetail> getValues() {
        return values;
    }

    public void setValues(Map<String, CurrentMonthDetail> values) {
        this.values = values;
    }

}
