package com.example.diploma;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

public class AntivirusChecker {
    public static boolean isAntivirusEnabled(Context context) throws IllegalAccessException, InstantiationException {
        PackageManager packageManager = context.getPackageManager();

        // Пакеты известных антивирусов для проверки их наличия
        String[] antivirusPackages = {"com.avast.android.mobilesecurity", "com.bitdefender.antivirus", "com.eset.ems2.gp",
                "com.kaspersky.mobile.antivirus", "com.lookout", "com.mcafee.android.av","com.symantec.mobilesecurity","com.psafe.msuite"};

        for (String packageName : antivirusPackages) {
            try {
                // Проверяем, установлен ли пакет антивируса на устройстве
                packageManager.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);

                // Если пакет антивируса установлен, значит антивирус включен
                return true;
            } catch (PackageManager.NameNotFoundException e) {

            }
        }

            return false;
    }
}