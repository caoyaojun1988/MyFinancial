package com.cao.output;

import com.cao.domain.AccountItem;
import com.cao.domain.ProjectItem;
import com.cao.input.ProjectItemInput;
import com.cao.input.ProjectItemInput;
import com.cao.input.TotalAcountInput;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoyaojun on 11/1/14.
 */
public class ZhiChuMingXiOutputTest extends TestCase {
    public void testImportExcel() throws Exception {
        String cell0Value = "1、(1) ,（1）adfs";
        String replaceAll = cell0Value.replaceAll("\\(?\\d*\\)?", "");

        String replace = StringUtils.replace(replaceAll, "、", "");
        String value = StringUtils.replace(replace, " ", "");
        System.out.print(value);

        ProjectItemInput currentMonthDetail = new ProjectItemInput();
        currentMonthDetail.setOrgurl("/home/caoyaojun/gitspace/test.excel/src/test/resources/9yf.xls");
//        List<AccountItem> currentMonthDetails = currentMonthDetail.importExcel();
//
//        Map<String, AccountItem> map = new HashMap<String, AccountItem>();
//        for (AccountItem monthDetail : currentMonthDetails) {
//            map.put(monthDetail.getName(), monthDetail);
//        }
//
//        ZhiChuMingXiOutput zhiChuMingXiOutput = new ZhiChuMingXiOutput();
//        zhiChuMingXiOutput.setBaseValues(map);
      //  zhiChuMingXiOutput.setOrgurl("/home/caoyaojun/gitspace/test.excel/src/test/resources/template-new.xls");
       // zhiChuMingXiOutput.setDesurl("/home/caoyaojun/gitspace/test.excel/src/test/resources/10-result-01.xls");
        //zhiChuMingXiOutput.CreateExcel();
    }
}
