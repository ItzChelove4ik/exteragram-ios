package com.google.android.play.integrity.internal;

/* loaded from: classes5.dex */
public abstract class ak extends aj {
    public static boolean a(Object r2, Object r3) {
        if (r2 != r3) goto L5;
        return true;
    L5:
        if (r2 != null) goto L7;
    L9:
        return false;
    L7:
        if (r2.equals(r3) == false) goto L9;
        return true;
    }
}
