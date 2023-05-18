package com.example.diploma;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class RootedOrNot {
    public static boolean isRooted(MainActivity mainActivity) {
        String buildTags = android.os.Build.TAGS;
        boolean rooted = (buildTags != null && buildTags.contains("test-keys"));
        if (!rooted) {
            try {
                File file = new File("/system/app/Superuser.apk");
                if (file.exists()) {
                    rooted = true;
                }
            } catch (Exception e) {
                rooted = false;
            }
            if (!rooted) {
                Process process = null;
                try {
                    process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
                    BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                    if (in.readLine() != null) {
                        rooted = true;
                    }
                } catch (Exception e) {
                    rooted = false;
                } finally {
                    if (process != null) {
                        process.destroy();
                    }
                }
            }
        }
        return rooted;
    }
}
