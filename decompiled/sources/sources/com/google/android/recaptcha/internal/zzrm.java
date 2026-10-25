package com.google.android.recaptcha.internal;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import okio.AsyncTimeout$$ExternalSyntheticBUOutline0;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;
import sun.misc.Unsafe;

/* loaded from: classes5.dex */
enum zzrm extends Enum implements zzrk {
    public static final zzrm zza = null;
    public static final zzrm zzb = null;
    private static final Unsafe zzc = null;
    private static final int zzd = 0;
    private static final /* synthetic */ zzrm[] zze = null;

    static {
        zzrm r0 = new zzrm("UNSAFE_LITTLE_ENDIAN", 0);
        zza = r0;
        zzrm r1 = new zzrm("UNSAFE_BIG_ENDIAN", 1);
        zzb = r1;
        zze = new zzrm[]{r0, r1};
        Unsafe r02 = Unsafe.getUnsafe();     // Catch: SecurityException -> L5
    L9:
        zzc = r02;
        zzd = r02.arrayBaseOffset(byte[].class);
        if (r02.arrayIndexScale(byte[].class) != 1) goto L12;
        return;
    L12:
        AsyncTimeout$$ExternalSyntheticBUOutline0.m();
        return;
    L5:
        zzrl r03 = zzrl.zza;     // Catch: Exception -> L14
        r02 = (Unsafe) Class.forName("java.security.AccessController").getMethod("doPrivileged", new Class[]{PrivilegedExceptionAction.class}).invoke(null, new Object[]{r03});     // Catch: Exception -> L8
    L8:
        r02 = zzc();     // Catch: Exception -> L14
        Unsafe r12 = r02;     // Catch: Exception -> L14
    L14:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Could not initialize intrinsics", e);
    }

    zzrm(String r1, int r2) {
    }

    public static zzrm[] values() {
        return (zzrm[]) zze.clone();
    }

    public static /* synthetic */ Unsafe zzb() {
        return zzc();
    }

    private static /* synthetic */ Unsafe zzc() {
        Field[] r1 = Unsafe.class.getDeclaredFields();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L10;
        Field r4 = r1[r3];
        r4.setAccessible(true);
        Object r42 = r4.get(null);
        if (Unsafe.class.isInstance(r42) == true) goto L7;
        r3 = r3 + 1;
        goto L3
    L7:
        return (Unsafe) Unsafe.class.cast(r42);
    L10:
        throw new NoSuchFieldError("the Unsafe");
    }

    @Override // com.google.android.recaptcha.internal.zzrk
    public final /* synthetic */ long zza(byte[] r5, int r6) {
        int r4 = ordinal();
        if (r4 == 0) goto L11;
        if (r4 != 1) goto L9;
        return Long.reverseBytes(zzc.getLong(r5, r6 + zzd));
    L9:
        throw null;
    L11:
        return zzc.getLong(r5, r6 + zzd);
    }
}
