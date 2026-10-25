package com.google.android.gms.cast.framework;

import java.util.Locale;

/* loaded from: classes4.dex */
public final class CastState {
    public static final int CONNECTED = 4;
    public static final int CONNECTING = 3;
    public static final int NOT_CONNECTED = 2;
    public static final int NO_DEVICES_AVAILABLE = 1;

    private CastState() {
    }

    public static String toString(int r2) {
        if (r2 != 1) goto L5;
        return "NO_DEVICES_AVAILABLE";
    L5:
        if (r2 != 2) goto L7;
        return "NOT_CONNECTED";
    L7:
        if (r2 != 3) goto L9;
        return "CONNECTING";
    L9:
        if (r2 != 4) goto L11;
        return "CONNECTED";
    L11:
        return String.format(Locale.ROOT, "UNKNOWN_STATE(%d)", new Object[]{Integer.valueOf(r2)});
    }
}
