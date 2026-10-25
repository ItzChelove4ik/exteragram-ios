package com.google.android.gms.internal.measurement;

import com.google.common.annotations.GoogleInternal;

/* loaded from: classes4.dex */
public abstract class zzyc implements zzyd {
    public static final zzyc zza = null;

    static {
        zza = new zzya();
    }

    public zzyc() {
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("LogSite{ class=");
        r0.append(zza());
        r0.append(", method=");
        r0.append(zzb());
        r0.append(", line=");
        r0.append(zzc());
        if (zzd() == null) goto L6;
        r0.append(", file=");
        r0.append(zzd());
    L6:
        if (zze() == null) goto L8;
        r0.append(", filePath=");
        r0.append(zze());
    L8:
        r0.append(" }");
        return r0.toString();
    }

    public abstract String zza();

    public abstract String zzb();

    public abstract int zzc();

    public abstract String zzd();

    @GoogleInternal
    public String zze() {
        return null;
    }
}
