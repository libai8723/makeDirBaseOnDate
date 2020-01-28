package io.github.libai8723;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class execCMD {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process p = Runtime.getRuntime().exec("REG QUERY HKEY_CLASSES_ROOT\\Directory\\background\\shell");
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(p.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = p.waitFor();
        System.out.println("\nExited with error code : " + exitCode);

    }
}
