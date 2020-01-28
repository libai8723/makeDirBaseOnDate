package io.github.libai8723;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class InstallationScriptTest
        extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public InstallationScriptTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(InstallationScriptTest.class);
    }

    public void testGetJavawFile() {
        String expected = "D:\\Java\\jdk1.8.0_221\\bin\\javaw.exe";
        assertEquals("get the jdk javaw file path", expected, InstallationScript.findJavawFile().getAbsolutePath());
    }

    public void testIsJAR(){
        boolean expected = false;
        assertEquals("shoud be true in IDE", expected, InstallationScript.isJAR());
    }

    public void testGenerateClassPathFromIDEClassesPath() {
        String expected = "C:\\Users\\Q\\Desktop\\makeDirBaseOnDate\\target\\classes\\";
        URL url = InstallationScript.class.getResource("InstallationScript.class");

        assertEquals("IDE classpath", expected, InstallationScript.generateClassPath(url, false));
    }

    public void testGenerateClassPathFromJarFile() throws MalformedURLException {
        String expected = "D:\\mysdk\\.m2\\repository\\io\\github\\libai8723\\makeDirBaseOnDate\\1.0-SNAPSHOT\\makeDirBaseOnDate-1.0-SNAPSHOT.jar";
        URL url = new URL("jar:file:/D:/mysdk/.m2/repository/io/github/libai8723/makeDirBaseOnDate/1.0-SNAPSHOT/makeDirBaseOnDate-1.0-SNAPSHOT.jar!/io/github/libai8723/InstallationScript.class");
        assertEquals("JAR Classpath", expected, InstallationScript.generateClassPath(url, true));
    }

    public void testCMDStringWithClassFile() {
        String expected = "\"D:\\Java\\jdk1.8.0_221\\bin\\javaw.exe\" -cp C:\\Users\\Q\\Desktop\\makeDirBaseOnDate\\target\\classes\\ io.github.libai8723.Main \"%V\"";
        File javawFile = new File("D:\\Java\\jdk1.8.0_221\\bin\\javaw.exe");
        boolean isJAR = false;
        URL url = InstallationScript.class.getResource("InstallationScript.class");
        assertEquals("cmd string with class file", expected, InstallationScript.generateCMDString(javawFile, url, isJAR));

    }

    public void testCMDStringWithJARFile() throws MalformedURLException {
        String expected = "\"D:\\Java\\jdk1.8.0_221\\bin\\javaw.exe\" -cp D:\\mysdk\\.m2\\repository\\io\\github\\libai8723\\makeDirBaseOnDate\\1.0-SNAPSHOT\\makeDirBaseOnDate-1.0-SNAPSHOT.jar io.github.libai8723.Main \"%V\"";
        File javawFile = new File("D:\\Java\\jdk1.8.0_221\\bin\\javaw.exe");
        boolean isJAR = true;
        URL url = new URL("jar:file:/D:/mysdk/.m2/repository/io/github/libai8723/makeDirBaseOnDate/1.0-SNAPSHOT/makeDirBaseOnDate-1.0-SNAPSHOT.jar!/io/github/libai8723/InstallationScript.class");
        assertEquals("cmd string with JAR file", expected, InstallationScript.generateCMDString(javawFile, url, isJAR));

    }

    public void testEscapeStrAndQuoteWithJavawPath() {
        String expected = "\"D:\\\\Java\\\\jdk1.8.0_221\\\\bin\\\\java.exe\"";
        String input = "D:\\Java\\jdk1.8.0_221\\bin\\java.exe";
        assertEquals("escape javaw path", expected, InstallationScript.escapeStrAndQuote(input));
    }

    public void testEscapeStrAndQuoteWithCmd() {
        String expected ="\"\\\"D:\\\\Java\\\\jdk1.8.0_221\\\\bin\\\\javaw.exe\\\" -cp C:\\\\Users\\\\Q\\\\Desktop\\\\makeDirBaseOnDate\\\\target\\\\classes\\\\ io.github.libai8723.Main \\\"%V\\\"\"";

        String input = "\"D:\\Java\\jdk1.8.0_221\\bin\\javaw.exe\" -cp C:\\Users\\Q\\Desktop\\makeDirBaseOnDate\\target\\classes\\ io.github.libai8723.Main \"%V\"";

        assertEquals("escape cmd path", expected, InstallationScript.escapeStrAndQuote(input));
    }
}
