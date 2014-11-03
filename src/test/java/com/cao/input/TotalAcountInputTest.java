package com.cao.input;

import com.cao.domain.AccountItem;
import junit.framework.TestCase;

import java.util.List;

/**
 * Created by caoyaojun on 11/1/14.
 */
public class TotalAcountInputTest extends TestCase {

    //@Test
    public void testImportExcel() throws Exception {
        TotalAcountInput totalAcountInput = new TotalAcountInput();
        totalAcountInput.setOrgurl("/home/caoyaojun/gitspace/test.excel/src/test/resources/10-accountitem.xls");
        List<AccountItem> accountItems =totalAcountInput.importExcel();
        System.out.print(accountItems.size());
    }
}
