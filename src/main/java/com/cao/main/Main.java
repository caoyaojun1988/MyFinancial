package com.cao.main;

import com.cao.domain.AccountItem;
import com.cao.domain.CurrentMonthDetail;
import com.cao.input.CurrentMonthDetailInput;
import com.cao.input.TotalAcountInput;
import com.cao.output.BalanceOutput;
import com.cao.output.ShouZhiOutput;
import com.cao.output.ZhiChuMingXiJinDuOutput;
import com.cao.output.ZhiChuMingXiOutput;
import com.sun.corba.se.spi.orbutil.fsm.Input;
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

    public boolean ToOpen(String templateUrl, String orgurlForTotalAccount, String orgurlForCurrentAccount, String orgurlForCurrentDetail, String desurl) {
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

            //当期明细
            CurrentMonthDetailInput currentMonthDetail = new CurrentMonthDetailInput();
            currentMonthDetail.setOrgurl(orgurlForCurrentDetail);
            List<CurrentMonthDetail> currentMonthDetails = currentMonthDetail.importExcel();
            Map<String, CurrentMonthDetail> map = new HashMap<String, CurrentMonthDetail>();
            for (CurrentMonthDetail monthDetail : currentMonthDetails) {
                map.put(monthDetail.getName(), monthDetail);
            }

            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(templateUrl)); // 读取文件流

            BalanceOutput balanceOutput = new BalanceOutput();
            balanceOutput.setValues1(totalAccountItemMap1);
            balanceOutput.setValues2(totalAccountItemMap2);
            balanceOutput.setValues3(totalAccountItemMap3);
            balanceOutput.setValues4(totalAccountItemMap4);
            balanceOutput.setValues5(totalAccountItemMap5);
            workbook = balanceOutput.CreateExcel(workbook);

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

            ZhiChuMingXiOutput zhiChuMingXiOutput = new ZhiChuMingXiOutput();
            zhiChuMingXiOutput.setValues(map);
            workbook = zhiChuMingXiOutput.CreateExcel(workbook);

            //当期明细进度
            ZhiChuMingXiJinDuOutput zhiChuMingXiJinDuOutput = new ZhiChuMingXiJinDuOutput();
            zhiChuMingXiJinDuOutput.setValues(map);
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
}
