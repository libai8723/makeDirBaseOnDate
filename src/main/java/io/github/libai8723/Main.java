package io.github.libai8723;

import javax.swing.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * a little tool to create a directory based on the current date
 */
public class Main
{
    public static void main( String[] args )
    {
        if(args.length < 1){
            // no enough command line argument
            System.exit(1);
        }else{
            for (String parameter: args) {
                System.out.println(parameter);
            }
            System.out.println();
            String DirName = getDirName();
            if(DirName != null) {
                System.out.println("Dir Name is:" + DirName);

                // args[0] is the context menu invoker's command line arguments
                // args[0] contains the context menu's living DIR
                String filepath = args[0] + File.separator + DirName;
                File f = new File(filepath);
                System.out.println("The Dir Path is:" + filepath);
                if (!f.exists()) {
                    boolean succeed = f.mkdir();
                    System.out.println("DIR Created Succeed: " + succeed);
                }
            }else
            {
                System.exit(1);
            }
        }
    }

    /**
     * get the DIR's name, which is going to be created
     * @return the trimed user input DIR name
     */
    private static String getDirName() {
        MyJDialog dialog = new MyJDialog(null, true);
        String input = dialog.getInputString();
        if(input != null){
            input = Util.removeInvalidCharacter4DIRName(input);
            input = Util.myTrim(input);
            if(input.length()>0)
                return Util.getCurDateStr(new Date()) +" "+ input;
         }
        return null;
    }



}
