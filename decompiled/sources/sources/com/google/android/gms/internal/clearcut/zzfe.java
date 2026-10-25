package com.google.android.gms.internal.clearcut;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
final class zzfe implements PrivilegedExceptionAction<Unsafe> {
    public zzfe() {
    }

    @Override // java.security.PrivilegedExceptionAction
    public final /* synthetic */ Unsafe run() {
        Field[] r0 = Unsafe.class.getDeclaredFields();
        int r1 = r0.length;
        int r2 = 0;
    L4:
        if (r2 >= r1) goto L10;
        Field r4 = r0[r2];
        r4.setAccessible(true);
        Object r3 = r4.get(null);
        if (Unsafe.class.isInstance(r3) == true) goto L8;
        r2 = r2 + 1;
        goto L4
    L8:
        return (Unsafe) Unsafe.class.cast(r3);
    L10:
        return null;
    }
}
