package com.google.android.gms.common.util;

import java.util.regex.Pattern;

/* loaded from: classes.dex */
public abstract class Strings {
    private static final Pattern zza = null;

    static {
        zza = Pattern.compile("\\$\\{(.*?)\\}");
    }

    public static boolean isEmptyOrWhitespace(String r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.trim().isEmpty() == true) goto L10;
        return false;
    L10:
        return true;
    }
}
