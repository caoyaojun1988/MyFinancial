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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws Exception {
        File file = new File("/home/caoyaojun/Downloads/xinyao.xls");
        FileInputStream fileInputStream = new FileInputStream(file);
        HashMap<Integer, List<KeyValue>> integerListHashMap = importExcel(fileInputStream);

        double sum1 = 0;
        double sum2 = 0;
        double sum3 = 0;
        int i = 0;
        for (Map.Entry<Integer, List<KeyValue>> integerListEntry : integerListHashMap.entrySet()) {
            i++;
            List<KeyValue> value = integerListEntry.getValue();
            int sumall = 0;
            for (KeyValue keyValue : value) {
                sumall += keyValue.getSumValue();
            }
            if (i == 1) {
                sum1 += sumall;
            } else if (i == 2) {
                sum2 += sumall;
            } else {
                sum3 += sumall;
            }
            System.out.println(integerListEntry.getKey() + " size =" + value.size() + " ;sum =" + sumall);
        }
        System.out.println(" sum1 =" + sum1);
        System.out.println(" sum2 =" + sum2);
        System.out.println(" sum3 =" + sum3);
        System.out.println(" sum1-2 =" + (sum1 - sum2));

        List<KeyValue> allResult = integerListHashMap.get(0);
        HashMap<String, KeyValue> allMap = new HashMap<String, KeyValue>();
        for (KeyValue keyValue : allResult) {
            allMap.put(keyValue.getKey(), keyValue);
        }
        integerListHashMap.remove(0);

        double sum = 0;
        double csum = 0;
        for (Map.Entry<Integer, List<KeyValue>> integerListEntry : integerListHashMap.entrySet()) {

            List<KeyValue> value = integerListEntry.getValue();
            for (KeyValue keyValue : value) {
                if (keyValue.getValue() == null || keyValue.getValue() <= 0.1) {
                    System.out.println("EEROR 1:KEY = " + keyValue.getKey() + "value1=" + allMap.get(keyValue.getKey()).getValue() + "value2=" + keyValue.getValue());
                } else if (allMap.get(keyValue.getKey()) == null || allMap.get(keyValue.getKey()).getValue() == null || allMap.get(keyValue.getKey()).getValue() <= 0.1) {
                    System.out.println("EEROR 2:KEY = " + keyValue.getKey() + "  sumvalue = " + keyValue.getSumValue());
                    sum = sum + keyValue.getSumValue();
                    csum = csum + keyValue.getNum() * keyValue.getValue();
                } else if (Math.abs(allMap.get(keyValue.getKey()).getValue().doubleValue() - keyValue.getValue().doubleValue()) < 0.00005) {
                    KeyValue keyValue1 = allMap.get(keyValue.getKey());
                    if (keyValue.getNum() == keyValue1.getNum() && Math.abs(keyValue.getSumValue() - keyValue1.getSumValue()) < 0.00005) {
                        allMap.remove(keyValue.getKey());
                    } else if (keyValue.getNum() <= keyValue1.getNum()) {
                        int i1 = keyValue1.getNum() - keyValue.getNum();
                        keyValue1.setNum(i1);
                    } else {
                        System.out.println("EEROR 4:KEY = " + keyValue.getKey() + "  sheet=" + integerListEntry.getKey() + "  num1=" + keyValue1.getNum() + "  num2= " + keyValue.getNum());
                    }
                } else {
                    System.out.println("EEROR 3:KEY = " + keyValue.getKey() + "value1=" + allMap.get(keyValue.getKey()).getValue() + "value2=" + keyValue.getValue());
                }
            }
        }

        System.out.println("congfu sum =" + sum + "congfu csum =" + csum);

        for (Map.Entry<String, KeyValue> stringKeyValueEntry : allMap.entrySet()) {
            if (stringKeyValueEntry.getValue().getNum() > 0) {
                System.out.println("error size =" + stringKeyValueEntry.getValue().getKey() + " num = " + stringKeyValueEntry.getValue().getNum());
            }
        }

        System.out.println("S3-sc +  sum =" + (sum3 - sum));

        System.out.println(" ==== =" + ((sum1 - sum2) - (sum3 - sum)));

    }

    private static HashMap<Integer, List<KeyValue>> importExcel(InputStream inputStream) throws Exception {

        HashMap<Integer, List<KeyValue>> result = new HashMap<Integer, List<KeyValue>>();
        // 获取工作薄workbook
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream); // 读取文件流
        // 获得 sheet总数
        int sheetCount = workbook.getNumberOfSheets();
        // 遍历sheet
        for (int sheetIndex = 0; sheetIndex < sheetCount - 1; sheetIndex++) {
            //   System.out.println("sheetIndex =" + sheetIndex);
            List<KeyValue> sheetList = new ArrayList<KeyValue>();
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
            for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
                //     System.out.println("rowIndex =" + rowIndex);
                // 获得行row对象
                HSSFRow row = sheet.getRow(rowIndex);
                // 如果此行为空，则进入下一个循环
                if (row == null) {
                    continue;
                }
                HSSFCell cell = row.getCell(0);
                if (cell == null) {
                    continue;
                }
                HSSFCell cell5 = row.getCell(5);
                int num = Double.valueOf(ExcelUtil.getString(cell5)).intValue();

                HSSFCell cell8;
                if (sheetIndex == 0 || sheetIndex == 1) {
                    cell8 = row.getCell(8);
                } else {
                    cell8 = row.getCell(7);
                }
                Double valueOFAll = Double.valueOf(ExcelUtil.getString(cell8));

                String tempResult = ExcelUtil.getString(cell).trim();
                List<String> result1 = exclus(tempResult);

                List<String> tempList = new ArrayList<String>();
                for (String s : result1) {
                    if (StringUtils.contains(s, "-")) {
                        String[] split = StringUtils.split(s, "-");
                        Integer begin = Integer.parseInt("1" + split[0]);
                        Integer end = Integer.parseInt("1" + split[1]);
                        for (int i = begin; i <= end; i++) {
                            tempList.add(String.valueOf(i).substring(1));
                        }
                    } else {
                        tempList.add(s);
                    }
                }

                if (tempList.size() == 1) {
                    KeyValue keymap = new KeyValue(tempList.get(0), num, valueOFAll);
                    sheetList.add(keymap);
                } else {
                    for (String s : tempList) {
                        KeyValue keymap = new KeyValue(s, 1, valueOFAll / tempList.size());
                        sheetList.add(keymap);
                    }
                }
            }

            result.put(sheetIndex, sheetList);
        }
        return result;
    }

    private static List<String> exclus(String tempResult) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.contains(tempResult, ".")) {
            for (String s : StringUtils.split(tempResult, ".")) {
                result.add(s);
            }
        } else if (StringUtils.contains(tempResult, ",")) {
            for (String s : StringUtils.split(tempResult, ",")) {
                result.add(s);
            }
        } else {
            result.add(tempResult);
        }
        return result;
    }
}

