package org.telegram.messenger.support.customtabsclient.shared;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.ApplicationLoader;

/* loaded from: classes.dex */
public abstract class CustomTabsHelper {
    private static String sPackageNameToUse;

    public static String getPackageNameToUse(Context r10) {
        String r0 = sPackageNameToUse;
        if (r0 == null) goto L5;
        return r0;
    L5:
        PackageManager r02 = r10.getPackageManager();
        Intent r1 = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo r3 = r02.resolveActivity(r1, 0);
        if (r3 == null) goto L8;
        String r32 = r3.activityInfo.packageName;
    L9:
        List<ResolveInfo> r5 = r02.queryIntentActivities(r1, 0);
        ArrayList r6 = new ArrayList();
        Iterator<ResolveInfo> r52 = r5.iterator();
    L11:
        if (r52.hasNext() == false) goto L16;
        ResolveInfo r7 = r52.next();
        Intent r8 = new Intent();
        r8.setAction("android.support.customtabs.action.CustomTabsService");
        r8.setPackage(r7.activityInfo.packageName);
        if (r02.resolveService(r8, 0) == null) goto L11;
        r6.add(r7.activityInfo.packageName);
        goto L11
    L16:
        if (r6.isEmpty() == false) goto L19;
        sPackageNameToUse = null;
    L50:
        if ("com.sec.android.app.sbrowser".equalsIgnoreCase(sPackageNameToUse) == false) goto L48;
        PackageManager r102 = ApplicationLoader.applicationContext.getPackageManager();     // Catch: Throwable -> L49
        ApplicationInfo r03 = r102.getApplicationInfo("com.android.chrome", 0);     // Catch: Throwable -> L49
        if (r03 == null) goto L48;
        if (r03.enabled == false) goto L48;
        r102.getPackageInfo("com.android.chrome", 1);     // Catch: Throwable -> L49
        sPackageNameToUse = "com.android.chrome";     // Catch: Throwable -> L49
    L48:
        return sPackageNameToUse;
    L19:
        if (r6.size() != 1) goto L22;
        sPackageNameToUse = (String) r6.get(0);
        goto L50
    L22:
        if (TextUtils.isEmpty(r32) == true) goto L29;
        if (hasSpecializedHandlerIntents(r10, r1) == true) goto L29;
        if (r6.contains(r32) == false) goto L29;
        sPackageNameToUse = r32;
    L29:
        if (r6.contains("com.android.chrome") == false) goto L32;
        sPackageNameToUse = "com.android.chrome";
        goto L50
    L32:
        if (r6.contains("com.chrome.beta") == false) goto L35;
        sPackageNameToUse = "com.chrome.beta";
        goto L50
    L35:
        if (r6.contains("com.chrome.dev") == false) goto L38;
        sPackageNameToUse = "com.chrome.dev";
        goto L50
    L38:
        if (r6.contains("com.google.android.apps.chrome") == false) goto L50;
        sPackageNameToUse = "com.google.android.apps.chrome";
        goto L50
    L8:
        r32 = null;
        goto L9
    }

    private static boolean hasSpecializedHandlerIntents(Context r3, Intent r4) {
        List<ResolveInfo> r32 = r3.getPackageManager().queryIntentActivities(r4, 64);     // Catch: RuntimeException -> L25
        if (r32 != null) goto L6;
    L24:
        return false;
    L6:
        if (r32.size() == 0) goto L24;
        Iterator<ResolveInfo> r33 = r32.iterator();     // Catch: RuntimeException -> L25
    L9:
        if (r33.hasNext() == false) goto L26;
        ResolveInfo r42 = r33.next();     // Catch: RuntimeException -> L25
        IntentFilter r1 = r42.filter;     // Catch: RuntimeException -> L25
        if (r1 == null) goto L9;
        if (r1.countDataAuthorities() == 0) goto L9;
        if (r1.countDataPaths() == 0) goto L9;
        if (r42.activityInfo == null) goto L9;
        return true;
    L26:
        return false;
    L25:
        Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
        goto L26
    }
}
