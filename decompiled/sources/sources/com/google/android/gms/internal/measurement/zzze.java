package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public abstract class zzze {
    public static /* synthetic */ boolean zza(int r1, zzyc r2, StringBuilder r3) {
        if ((r1 - 1) == 0) goto L6;
        return false;
    L6:
        if (r2 != zzyc.zza) goto L8;
        return false;
    L8:
        r3.append(r2.zza());
        r3.append('.');
        r3.append(r2.zzb());
        r3.append(':');
        r3.append(r2.zzc());
        return true;
    }
}
