package org.telegram.messenger;

import android.os.Build;
import java.lang.reflect.Field;

/* loaded from: classes5.dex */
public class OneUIUtilities {
    public static final int ONE_UI_4_0 = 40000;
    private static Boolean isOneUI;
    private static int oneUIEncodedVersion;

    public OneUIUtilities() {
    }

    public static int getOneUIEncodedVersion() {
        if (isOneUI() == true) goto L7;
        return 0;
    L7:
        return oneUIEncodedVersion;
    }

    public static boolean hasBuiltInClipboardToasts() {
        if (isOneUI() == true) goto L5;
        return false;
    L5:
        if (getOneUIEncodedVersion() != 40000) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isOneUI() {
        Boolean r0 = isOneUI;
        if (r0 != null) goto L5;
        Field r02 = Build.VERSION.class.getDeclaredField("SEM_PLATFORM_INT");     // Catch: Exception -> L12
        r02.setAccessible(true);     // Catch: Exception -> L12
        int r03 = ((Integer) r02.get(null)).intValue();     // Catch: Exception -> L12
        if (r03 >= 100000) goto L10;
        return false;
    L10:
        oneUIEncodedVersion = r03 - 90000;     // Catch: Exception -> L12
        isOneUI = Boolean.TRUE;     // Catch: Exception -> L12
    L14:
        return isOneUI.booleanValue();
    L12:
        isOneUI = Boolean.FALSE;
        goto L14
    L5:
        return r0.booleanValue();
    }
}
