package com.example.diploma;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class AppPermissionsChecker {
    public static List<String> getAppsUsingMicrophoneAndCamera(Context context) {
        List<String> appsList = new ArrayList<>();

        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);

        for (PackageInfo packageInfo : installedPackages) {
            String[] permissions = packageInfo.requestedPermissions;
            if (permissions != null) {
                for (String permission : permissions) {
                    if (permission.equals(Manifest.permission.CAMERA) || permission.equals(Manifest.permission.RECORD_AUDIO)) {
                        appsList.add(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                        break;
                    }
                }
            }
        }

        return appsList;
    }
}
