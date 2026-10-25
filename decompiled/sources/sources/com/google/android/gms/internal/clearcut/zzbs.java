package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
abstract class zzbs {
    private static final Class<?> zzgl = null;

    static {
        zzgl = zzak();
    }

    private static Class<?> zzak() {
        return Class.forName("com.google.protobuf.ExtensionRegistry");
    L4:
        return null;
    }

    public static zzbt zzal() {
        Class<?> r0 = zzgl;
        if (r0 == null) goto L7;
        return (zzbt) r0.getDeclaredMethod("getEmptyRegistry", null).invoke(null, null);
    L7:
        return zzbt.zzgo;
    }
}
