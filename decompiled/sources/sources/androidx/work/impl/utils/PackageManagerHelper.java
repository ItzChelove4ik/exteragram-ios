package androidx.work.impl.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import androidx.work.Logger;

/* loaded from: classes.dex */
public abstract class PackageManagerHelper {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("PackageManagerHelper");
    }

    private static int getComponentEnabledSetting(Context r2, String r3) {
        return r2.getPackageManager().getComponentEnabledSetting(new ComponentName(r2, r3));
    }

    private static boolean isComponentEnabled(int r0, boolean r1) {
        if (r0 != 0) goto L5;
        return r1;
    L5:
        if (r0 != 1) goto L7;
        return true;
    L7:
        return false;
    }

    public static void setComponentEnabled(Context r5, Class<?> r6, boolean r7) {
        String r0 = "disabled";
    L7:
        e = move-exception;
        Logger r2 = Logger.get();
        String r3 = TAG;
        StringBuilder r4 = new StringBuilder();
        r4.append(r6.getName());
        r4.append("could not be ");
        if (r7 == false) goto L22;
        r0 = "enabled";
    L22:
        r4.append(r0);
        r2.debug(r3, r4.toString(), e);
        return;
    L4:
        if (r7 != isComponentEnabled(getComponentEnabledSetting(r5, r6.getName()), false)) goto L9;
        Logger.get().debug(TAG, "Skipping component enablement for ".concat(r6.getName()));     // Catch: Exception -> L7
        return;
    L9:
        PackageManager r22 = r5.getPackageManager();     // Catch: Exception -> L7
        ComponentName r32 = new ComponentName(r5, r6.getName());     // Catch: Exception -> L7
        if (r7 == false) goto L12;
        int r42 = 1;
    L13:
        r22.setComponentEnabledSetting(r32, r42, 1);     // Catch: Exception -> L7
        Logger r52 = Logger.get();     // Catch: Exception -> L7
        String r23 = TAG;     // Catch: Exception -> L7
        StringBuilder r33 = new StringBuilder();     // Catch: Exception -> L7
        r33.append(r6.getName());     // Catch: Exception -> L7
        r33.append(" ");     // Catch: Exception -> L7
        if (r7 == false) goto L16;
        String r43 = "enabled";
    L17:
        r33.append(r43);     // Catch: Exception -> L7
        r52.debug(r23, r33.toString());     // Catch: Exception -> L7
        return;
    L16:
        r43 = "disabled";
        goto L17
    L12:
        r42 = 2;
        goto L13
    }
}
