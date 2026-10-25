package com.google.firebase.crashlytics.internal.common;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.google.firebase.crashlytics.internal.DevelopmentPlatformProvider;
import java.util.List;

/* loaded from: classes.dex */
public class AppData {
    public final String buildId;
    public final List<BuildIdInfo> buildIdInfoList;
    public final DevelopmentPlatformProvider developmentPlatformProvider;
    public final String googleAppId;
    public final String installerPackageName;
    public final String packageName;
    public final String versionCode;
    public final String versionName;

    public AppData(String r1, String r2, List<BuildIdInfo> r3, String r4, String r5, String r6, String r7, DevelopmentPlatformProvider r8) {
        this.googleAppId = r1;
        this.buildId = r2;
        this.buildIdInfoList = r3;
        this.installerPackageName = r4;
        this.packageName = r5;
        this.versionCode = r6;
        this.versionName = r7;
        this.developmentPlatformProvider = r8;
    }

    public static AppData create(Context r9, IdManager r10, String r11, String r12, List<BuildIdInfo> r13, DevelopmentPlatformProvider r14) {
        String r5 = r9.getPackageName();
        String r4 = r10.getInstallerPackageName();
        PackageInfo r92 = r9.getPackageManager().getPackageInfo(r5, 0);
        String r6 = getAppBuildVersion(r92);
        String r93 = r92.versionName;
        if (r93 != null) goto L6;
        r93 = "0.0";
    L6:
        return new AppData(r11, r12, r13, r4, r5, r6, r93, r14);
    }

    private static String getAppBuildVersion(PackageInfo r2) {
        if (Build.VERSION.SDK_INT < 28) goto L7;
        return Long.toString(r2.getLongVersionCode());
    L7:
        return Integer.toString(r2.versionCode);
    }
}
