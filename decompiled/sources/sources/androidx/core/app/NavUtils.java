package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/* loaded from: classes3.dex */
public abstract class NavUtils {
    public static Intent getParentActivityIntent(Activity r3) {
        Intent r0 = r3.getParentActivityIntent();
        if (r0 == null) goto L5;
        return r0;
    L5:
        String r02 = getParentActivityName(r3);
        if (r02 != null) goto L8;
        return null;
    L8:
        ComponentName r2 = new ComponentName(r3, r02);
    L15:
        Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + r02 + "' in manifest");
        return null;
    L10:
        if (getParentActivityName(r3, r2) != null) goto L14;
        return Intent.makeMainActivity(r2);
    L14:
        return new Intent().setComponent(r2);
    }

    public static Intent getParentActivityIntent(Context r2, ComponentName r3) {
        String r0 = getParentActivityName(r2, r3);
        if (r0 != null) goto L6;
        return null;
    L6:
        ComponentName r1 = new ComponentName(r3.getPackageName(), r0);
        if (getParentActivityName(r2, r1) != null) goto L11;
        return Intent.makeMainActivity(r1);
    L11:
        return new Intent().setComponent(r1);
    }

    public static String getParentActivityName(Activity r1) {
        return getParentActivityName(r1, r1.getComponentName());
    L4:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static String getParentActivityName(Context r3, ComponentName r4) {
        PackageManager r0 = r3.getPackageManager();
        if (Build.VERSION.SDK_INT < 29) goto L5;
        int r1 = 269222528;
    L6:
        ActivityInfo r42 = r0.getActivityInfo(r4, r1);
        String r02 = r42.parentActivityName;
        if (r02 == null) goto L9;
        return r02;
    L9:
        Bundle r43 = r42.metaData;
        if (r43 != null) goto L12;
        return null;
    L12:
        String r44 = r43.getString("android.support.PARENT_ACTIVITY");
        if (r44 != null) goto L16;
        return null;
    L16:
        if (r44.charAt(0) == '.') goto L18;
        return r44;
    L18:
        return r3.getPackageName() + r44;
    L5:
        r1 = 787072;
        goto L6
    }

    @Deprecated
    public static void navigateUpTo(Activity r0, Intent r1) {
        r0.navigateUpTo(r1);
    }

    @Deprecated
    public static boolean shouldUpRecreateTask(Activity r0, Intent r1) {
        return r0.shouldUpRecreateTask(r1);
    }
}
