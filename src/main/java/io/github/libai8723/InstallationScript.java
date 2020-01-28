package io.github.libai8723;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.temporal.TemporalAccessor;

public class InstallationScript {
    public static void main(String[] args) throws IOException{
        generateWinRegistryFile();
    }

    /**
     * generate the win registry file
     * @throws IOException when there is no permission to create a .reg file
     */
    public static void generateWinRegistryFile() throws IOException {
        String TemplateString = "Windows Registry Editor Version 5.00\n" +
                "\n" +
                "[HKEY_CLASSES_ROOT\\Directory\\Background\\shell\\MakeNewDir]\n" +
                "@=\"Make new directory...\"\n" +
                "\"icon\"=${javawpath}\n" +
                "\n" +
                "[HKEY_CLASSES_ROOT\\Directory\\Background\\shell\\MakeNewDir\\command]\n" +
                "@=${cmd}";

        String javawpath = escapeStrAndQuote(findJavawFile().getPath());
        String cmd = escapeStrAndQuote(generateCMDString(findJavawFile(), InstallationScript.class.getResource("InstallationScript.class"), isJAR()));



        TemplateString = TemplateString.replace("${javawpath}", javawpath);
        TemplateString = TemplateString.replace("${cmd}", cmd);

        File f = new File("install.reg");
        PrintWriter pw = new PrintWriter(new FileWriter(f));
        pw.print(TemplateString);
        pw.close();
    }

    /**
     * generate the CMD string
     * @param javawFile the java runtime java.exe/javaw.exe
     * @param url the url of this class, because url includes the location information like file:D:/xxx/yyy/zzz/a.class
     * @param isJAR whether this class is in a JAR Package
     * @return the CMD used to invoke main class with classpath set
     */
    public static String generateCMDString(File javawFile, URL url, boolean isJAR) {
        return "\"" + javawFile.getAbsolutePath().replaceAll("/", "\\") + "\" -cp " +
                generateClassPath(url, isJAR) + " io.github.libai8723.Main \"%V\"";
    }

    /**
     * escape the quote and backslash character, then add heading and tailing quote
     * @param str the input str
     * @return the escaped str
     */
    public static String escapeStrAndQuote(String str) {
        String reValue = str.replaceAll("\\\\","\\\\\\\\");
        reValue = reValue.replace("\"","\\\"");
        return "\"" + reValue + "\"";
    }



    /**
     * generate the class path
     * @return the classpath string
     */
    public static String generateClassPath(URL url, boolean isJAR){
        if(isJAR){
            String fullPath = url.getPath();
            int indexOfPackgeName = fullPath.indexOf("!/io/github/libai8723/");
            int indexOfFileProtocal = fullPath.indexOf("file:/");
            return fullPath.substring(indexOfFileProtocal + 6, indexOfPackgeName).replaceAll("[/]","\\\\");
        }else{
            String fullPath= url.getPath();
            return fullPath.substring(1, fullPath.indexOf("io/github/libai8723")).replaceAll("/","\\\\");
        }
    }

    /**
     * whether this class in contained in a JAR package file
     * @return true if this class in contained in a JAR file
     */
    public static boolean isJAR(){
        URL url = InstallationScript.class.getResource("InstallationScript.class");
        return url.getProtocol().equals("jar");
    }


    /**
     * find the current javaw file path, return it as a java.io.File
     * @return a File object denote the javaw.exe
     */
    public static File findJavawFile(){
        String jdkHome;
        String jreHome;
        String oracleJavaPath= null;

        jdkHome = System.getenv("JAVA_HOME");
        if(jdkHome != null) {
            File f = new File(jdkHome + File.separator + "bin/javaw.exe");
            if(f.exists()) {
                return f;
            }
        }

        jreHome = System.getenv("JRE_HOME");
        if(jreHome != null){
            File f = new File(jreHome + File.separator + "bin/javaw.exe");
            if(f.exists()) {
                return f;
            }
        }

        String[] Paths = System.getenv("path").split(File.pathSeparator);
        for (String p: Paths
             ) {
            if(p.contains("javapath")) {
                oracleJavaPath = p;
            }
        }
        if(oracleJavaPath != null){
            File f = new File(oracleJavaPath + File.separator + "javaw.exe");
            if(f.exists())
                return f;
        }

        System.out.println("No Java is Found on this host");
        System.exit(1);

        return null;
    }
}
