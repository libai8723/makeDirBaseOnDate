package io.github.libai8723;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class UtilTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UtilTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(UtilTest.class);
    }

    /**
     * test the get current date string
     */
    public void testGetCurDateStr() {
        long dateTime = 1579963842829L;
        String dStr1 = "2020_01_25 22_50_42";
        Date date = new Date(dateTime);
        assertEquals(dStr1, Util.getCurDateStr(date));
    }

    /**
     * test myTrim Method with empty str
     */
    public void testMyTrimWithEmptyString() {
        String input = "";
        String expected = "";
        assertEquals("empty str in, empty str out", expected, Util.myTrim(input));
    }

    /**
     * test myTrim Method with single space str
     */
    public void testMyTrimWithSingleSpaceString() {
        String input = " ";
        String expected = "";
        assertEquals("single space in, empty str out", expected, Util.myTrim(input));
    }

    /**
     * test myTrim Method with double space str
     */
    public void testMyTrimWithDoubleSpaceString() {
        String input = "  ";
        String expected = "";
        assertEquals("double space str in, empty str out", expected, Util.myTrim(input));
    }

    /**
     * test myTrim Method with leading one space
     */
    public void testMyTrimWithLeadingOneSpace() {
        String input = " a";
        String expected = "a";
        assertEquals("leading one space removed", expected, Util.myTrim(input));
    }

    /**
     * test myTrim Method with leading one space
     */
    public void testMyTrimWithLeadingTwoSpace() {
        String input = "  a";
        String expected = "a";
        assertEquals("leading two space removed", expected, Util.myTrim(input));
    }

    /**
     * test myTrim Method with leading one space and tailing one space
     */
    public void testMyTrimWithLeadingAndTailingSpace() {
        String input = " a ";
        String expected = "a";
        assertEquals("Leading&Tailing Space Removed", expected, Util.myTrim(input));
    }

    /**
     * test myTrim Method with mid-space reserved
     */
    public void testMyTrimWithMidSpaceReserved() {
        String input = "a a";
        String expected = "a a";
        assertEquals("mid-space reserved", expected, Util.myTrim(input));
    }

    /**
     * test myTrim Method with mid-2-space trimed
     */
    public void testMyTrimWithMid2SpaceTrimed() {
        String input = "a  a";
        String expected = "a a";
        assertEquals("mid-2-space trimed", expected, Util.myTrim(input));
    }

    public void testMyTrimWithLeadingTab() {
        String input = "\ta";
        String expected = "a";
        assertEquals("Leading Tab Removed", expected, Util.myTrim(input));
    }

    public void testMyTrimWithLeadingTwoTab() {
        String input = "\t\ta";
        String expected = "a";
        assertEquals("Leading Two Tab Removed", expected, Util.myTrim(input));
    }

    public void testMyTrimWithLeadingAndTailingTab() {
        String input = "\ta\t";
        String expected = "a";
        assertEquals("Leading&Tailing Tab removed", expected, Util.myTrim(input));
    }

    public void testMyTrimWithNewLineChar() {
        String input = "aa\r\naa";
        String expected = "aa aa";
        assertEquals("NewLine Char replaced with space", expected, Util.myTrim(input));
    }

    public void testMyTrimWithNewLineReserved() {
        String input = "aa\r\naa";
        String expected = "aa\r\naa";
        assertEquals("NewLine Char replaced with space", expected, Util.myTrim(input, false));
    }

    public void testRemoveInvalidCharBackSlash() {
        String input = "\\";
        String expected = "";
        assertEquals("back slash removed",expected, Util.removeInvalidCharacter4DIRName(input));
    }

    public void testRemoveInvalidCharColon() {
        String input = "\\:";
        String expected = "";
        assertEquals("back slash and colon removed",expected, Util.removeInvalidCharacter4DIRName(input));
    }
}
