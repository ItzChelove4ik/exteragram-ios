package org.telegram.messenger;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Process;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes5.dex */
public final class PackageValidator {
    private static final Set<String> KNOWN_PACKAGES = null;

    static {
        HashSet r0 = new HashSet();
        KNOWN_PACKAGES = r0;
        r0.add("com.google.android.projection.gearhead");
        r0.add("com.google.android.mediasimulator");
        r0.add("com.android.car.media");
        r0.add("com.android.car.carlauncher");
        r0.add("com.google.android.car.kitchensink");
        r0.add("com.google.android.wearable.app");
        r0.add("com.google.android.wearable.media.sessions");
        r0.add("com.google.android.googlequicksearchbox");
        r0.add("com.google.android.apps.gsa.staticplugins");
        r0.add("com.google.android.bluetooth");
    }

    private PackageValidator() {
    }

    private static boolean hasPermission(Context r1, String r2, int r3) {
        PackageManager r12 = r1.getPackageManager();
        if (r12.checkPermission("android.permission.MEDIA_CONTENT_CONTROL", r2) != 0) goto L6;
        return true;
    L6:
        if (r12.checkPermission("android.permission.BIND_NOTIFICATION_LISTENER_SERVICE", r2) != 0) goto L9;
        return true;
    L9:
        return false;
    L14:
        return false;
    }

    public static boolean isKnownCaller(Context r2, String r3, int r4) {
        if (r3 != null) goto L6;
        return false;
    L6:
        if (r4 != 1000) goto L8;
    L15:
        return true;
    L8:
        if (r4 == Process.myUid()) goto L15;
        if (KNOWN_PACKAGES.contains(r3) == false) goto L14;
        return true;
    L14:
        return hasPermission(r2, r3, r4);
    }
}
