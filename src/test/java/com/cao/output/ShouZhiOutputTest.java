package com.cao.output;

import com.cao.domain.AccountItem;
import com.cao.input.TotalAcountInput;
import junit.framework.TestCase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoyaojun on 11/1/14.
 */
public class ShouZhiOutputTest extends TestCase {
    public void testImportExcel() throws Exception {
        TotalAcountInput totalAcountInput = new TotalAcountInput();
        totalAcountInput.setOrgurl("/home/caoyaojun/gitspace/test.excel/src/test/resources/10-accountitem.xls");
        List<AccountItem> accountItems = totalAcountInput.importExcel();
        Map<String, AccountItem> accountItemMap1 = new HashMap<String, AccountItem>();
        Map<String, AccountItem> accountItemMap2 = new HashMap<String, AccountItem>();
        Map<String, AccountItem> accountItemMap3 = new HashMap<String, AccountItem>();
        Map<String, AccountItem> accountItemMap4 = new HashMap<String, AccountItem>();
        Map<String, AccountItem> accountItemMap5 = new HashMap<String, AccountItem>();
        for (AccountItem accountItem : accountItems) {
            char c = accountItem.getId().toString().charAt(0);

            switch (c) {
                case '1':
                    accountItemMap1.put(accountItem.getName().trim(), accountItem);
                    break;
                case '2':
                    accountItemMap2.put(accountItem.getName().trim(), accountItem);
                    break;
                case '3':
                    accountItemMap3.put(accountItem.getName().trim(), accountItem);
                    break;
                case '4':
                    accountItemMap4.put(accountItem.getName().trim(), accountItem);
                    break;
                case '5':
                    accountItemMap5.put(accountItem.getName().trim(), accountItem);
                    break;
                default:
            }

        }

        BalanceOutput balanceOutput = new BalanceOutput();
        balanceOutput.setValues1(accountItemMap1);
        balanceOutput.setValues2(accountItemMap2);
        balanceOutput.setValues3(accountItemMap3);
        balanceOutput.setValues4(accountItemMap4);
        balanceOutput.setValues5(accountItemMap5);
      ////  balanceOutput.setOrgurl("/home/caoyaojun/gitspace/test.excel/src/test/resources/template-new.xls");
       // balanceOutput.setDesurl("/home/caoyaojun/gitspace/test.excel/src/test/resources/10-result-01.xls");

       // balanceOutput.CreateExcel();
    }
}
