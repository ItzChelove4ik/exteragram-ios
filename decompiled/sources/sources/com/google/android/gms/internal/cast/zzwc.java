package com.google.android.gms.internal.cast;

import com.google.android.gms.internal.cast.zzwb;
import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import sun.misc.Unsafe;

/* loaded from: classes4.dex */
final /* synthetic */ class zzwc implements PrivilegedExceptionAction {
    static final /* synthetic */ zzwc zza = null;

    static {
        zza = new zzwc();
    }

    private /* synthetic */ zzwc() {
    }

    @Override // java.security.PrivilegedExceptionAction
    public final /* synthetic */ Object run() {
        Unsafe r5 = zzwb.zzd.zza;
        Field[] r0 = Unsafe.class.getDeclaredFields();
        int r1 = r0.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L10;
        Field r3 = r0[r2];
        r3.setAccessible(true);
        Object r32 = r3.get(null);
        if (Unsafe.class.isInstance(r32) == true) goto L7;
        r2 = r2 + 1;
        goto L3
    L7:
        return (Unsafe) Unsafe.class.cast(r32);
    L10:
        throw new NoSuchFieldError("the Unsafe");
    }
}
