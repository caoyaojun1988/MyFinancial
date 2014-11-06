package com.cao;

import com.cao.util.ExcelUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest
  extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        Double a = 2392298.51;
        Long l = ((Double) (a * 100)).longValue();
        ;

        System.out.println(a );
        System.out.println(a * 100);
        System.out.println(l / 100.d);
        assertTrue(true);
    }
}
