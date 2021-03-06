package com.cao.main;

import com.cao.domain.AccountItem;
import com.cao.domain.ProjectItem;
import com.cao.input.ProjectItemInput;
import com.cao.input.TotalAcountInput;
import com.cao.output.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoyaojun on 11/1/14.
 */
public class Main {

    public boolean ToOpen(String templateUrl, String orgurlForTotalAccount, String orgurlForCurrentAccount, String orgurlForCurrentDetail, String orgurlForProject, String orgurlForProjectDetail, String desurl) {
        try {

            // 历史总账表
            TotalAcountInput totalAcountInput = new TotalAcountInput();
            totalAcountInput.setOrgurl(orgurlForTotalAccount);
            List<AccountItem> accountItems = totalAcountInput.importExcel();
            Map<String, AccountItem> totalAccountItemMap1 = new HashMap<String, AccountItem>();
            Map<String, AccountItem> totalAccountItemMap2 = new HashMap<String, AccountItem>();
            Map<String, AccountItem> totalAccountItemMap3 = new HashMap<String, AccountItem>();
            Map<String, AccountItem> totalAccountItemMap4 = new HashMap<String, AccountItem>();
            Map<String, AccountItem> totalAccountItemMap5 = new HashMap<String, AccountItem>();
            for (AccountItem accountItem : accountItems) {
                char c = accountItem.getId().toString().charAt(0);

                switch (c) {
                    case '1':
                        totalAccountItemMap1.put(accountItem.getName().trim(), accountItem);
                        break;
                    case '2':
                        totalAccountItemMap2.put(accountItem.getName().trim(), accountItem);
                        break;
                    case '3':
                        totalAccountItemMap3.put(accountItem.getName().trim(), accountItem);
                        break;
                    case '4':
                        totalAccountItemMap4.put(accountItem.getName().trim(), accountItem);
                        break;
                    case '5':
                        totalAccountItemMap5.put(accountItem.getName().trim(), accountItem);
                        break;
                    default:
                }
            }

            // 当期
            TotalAcountInput currentAcountInput = new TotalAcountInput();
            currentAcountInput.setOrgurl(orgurlForCurrentAccount);
            List<AccountItem> currentAccountItems = currentAcountInput.importExcel();
            Map<String, AccountItem> currentAccountItemMap1 = new HashMap<String, AccountItem>();
            Map<String, AccountItem> currentAccountItemMap2 = new HashMap<String, AccountItem>();
            Map<String, AccountItem> currentAccountItemMap3 = new HashMap<String, AccountItem>();
            Map<String, AccountItem> currentAccountItemMap4 = new HashMap<String, AccountItem>();
            Map<String, AccountItem> currentAccountItemMap5 = new HashMap<String, AccountItem>();
            for (AccountItem accountItem : currentAccountItems) {
                char c = accountItem.getId().toString().charAt(0);

                switch (c) {
                    case '1':
                        currentAccountItemMap1.put(accountItem.getName().trim(), accountItem);
                        break;
                    case '2':
                        currentAccountItemMap2.put(accountItem.getName().trim(), accountItem);
                        break;
                    case '3':
                        currentAccountItemMap3.put(accountItem.getName().trim(), accountItem);
                        break;
                    case '4':
                        currentAccountItemMap4.put(accountItem.getName().trim(), accountItem);
                        break;
                    case '5':
                        currentAccountItemMap5.put(accountItem.getName().trim(), accountItem);
                        break;
                    default:
                }
            }
            // 当期项目
            TotalAcountInput currentMonthProject = new TotalAcountInput();
            currentMonthProject.setOrgurl(orgurlForProject);
            List<AccountItem> currentMonthProjects = currentMonthProject.importExcel();
            Map<String, AccountItem> currentMonthProjectMap = new HashMap<String, AccountItem>();
            for (AccountItem monthDetail : currentMonthProjects) {
                currentMonthProjectMap.put(monthDetail.getName(), monthDetail);
            }

            //当期基础明细
            TotalAcountInput currentMonthDetail = new TotalAcountInput();
            currentMonthDetail.setOrgurl(orgurlForCurrentDetail);
            List<AccountItem> currentMonthDetails = currentMonthDetail.importExcel();
            Map<String, AccountItem> map = new HashMap<String, AccountItem>();
            for (AccountItem monthDetail : currentMonthDetails) {
                map.put(monthDetail.getName(), monthDetail);
            }
            // 当前项目明显
            ProjectItemInput projectItemInput = new ProjectItemInput();
            projectItemInput.setOrgurl(orgurlForProjectDetail);
            ProjectItem projectItem = projectItemInput.importExcel();
            Map<String, Double> projectMap = new HashMap<String, Double>();
            projectMap.put("文物采集及管理专项经费", projectItem.getCaiJi());
            projectMap.put("年度基本陈列和临时展览专项经费", projectItem.getChenLie());
            projectMap.put("财税文化文物研究和宣传", projectItem.getYanJiu());

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(templateUrl)); // 读取文件流
            //1
            BalanceOutput balanceOutput = new BalanceOutput();
            balanceOutput.setValues1(totalAccountItemMap1);
            balanceOutput.setValues2(totalAccountItemMap2);
            balanceOutput.setValues3(totalAccountItemMap3);
            balanceOutput.setValues4(totalAccountItemMap4);
            balanceOutput.setValues5(totalAccountItemMap5);
            workbook = balanceOutput.CreateExcel(workbook);
            //2
            ShouZhiOutput shouZhiOutput = new ShouZhiOutput();
            shouZhiOutput.setCurrentValues1(currentAccountItemMap1);
            shouZhiOutput.setCurrentValues2(currentAccountItemMap2);
            shouZhiOutput.setCurrentValues3(currentAccountItemMap3);
            shouZhiOutput.setCurrentValues4(currentAccountItemMap4);
            shouZhiOutput.setCurrentValues5(currentAccountItemMap5);

            shouZhiOutput.setValues1(totalAccountItemMap1);
            shouZhiOutput.setValues2(totalAccountItemMap2);
            shouZhiOutput.setValues3(totalAccountItemMap3);
            shouZhiOutput.setValues4(totalAccountItemMap4);
            shouZhiOutput.setValues5(totalAccountItemMap5);

            workbook = shouZhiOutput.CreateExcel(workbook);
            //3
            ZhiChuMingXiOutput zhiChuMingXiOutput = new ZhiChuMingXiOutput();
            zhiChuMingXiOutput.setBaseValues(map);
            zhiChuMingXiOutput.setProjectValues(currentMonthProjectMap);
            workbook = zhiChuMingXiOutput.CreateExcel(workbook);

            //4
            ZhiChuJinDuOutput zhiChuJinDuOutput = new ZhiChuJinDuOutput();
            zhiChuJinDuOutput.setValues(projectMap);
            workbook = zhiChuJinDuOutput.CreateExcel(workbook);

            //5 当期明细进度
            ZhiChuMingXiJinDuOutput zhiChuMingXiJinDuOutput = new ZhiChuMingXiJinDuOutput();
            zhiChuMingXiJinDuOutput.setBaseValues(map);
            zhiChuMingXiJinDuOutput.setProjectValues(currentMonthProjectMap);
            workbook = zhiChuMingXiJinDuOutput.CreateExcel(workbook);

            FileOutputStream fileOutputStream = new FileOutputStream(new File(desurl));
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            UI.showMessage(e.getMessage());
            return false;
        }
    }


    public static void main(String[] ar) {
        String templateUrl = "/Users/caoyaojun/Documents/lxy/template-new.xls";
        String orgurlForTotalAccount = "/Users/caoyaojun/Documents/lxy/dnzz.xls";
        String orgurlForCurrentAccount = "/Users/caoyaojun/Documents/lxy/dyzz.xls";
        String orgurlForCurrentDetail = "/Users/caoyaojun/Documents/lxy/dyjb.xls";
        String orgurlForProject = "/Users/caoyaojun/Documents/lxy/dyxm.xls";
        String orgurlForProjectDetail = "/Users/caoyaojun/Documents/lxy/xmmx.xls";
        String desurl = "/Users/caoyaojun/Documents/lxy/teest.xls";
        new Main().ToOpen(templateUrl, orgurlForTotalAccount, orgurlForCurrentAccount, orgurlForCurrentDetail, orgurlForProject, orgurlForProjectDetail,desurl);
    }
}
