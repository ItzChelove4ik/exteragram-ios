package com.google.android.gms.internal.mlkit_genai_prompt;

import com.google.android.gms.cast.internal.zzar$$ExternalSyntheticBUOutline1;
import java.util.Objects;

/* loaded from: classes4.dex */
public abstract class zziw {
    public zziw() {
    }

    public static zziw zzh(zzcy r4) {
        int r0 = r4.zza();
        if (r0 != 0) goto L5;
        String r42 = r4.zzb();
        Objects.requireNonNull(r42);
        return new zziq(r42);
    L5:
        if (r0 == 1) goto L28;
        if (r0 == 2) goto L26;
        if (r0 == 3) goto L24;
        if (r0 != 4) goto L13;
        int r02 = zzml.$r8$clinit;
        zzmh r03 = new zzmh();
        zzml r43 = r4.zzf();
        int r1 = r43.size();
        int r2 = 0;
    L19:
        if (r2 >= r1) goto L21;
        r03.zzd(zzh((zzcy) r43.get(r2)));
        r2 = r2 + 1;
        goto L19
    L21:
        zzml r44 = zzml.zzq(r03.zze());
        Objects.requireNonNull(r44);
        return new zzio(r44);
    L13:
        if (r0 == 5) goto L15;
        int r45 = r4.zza();
        zzar$$ExternalSyntheticBUOutline1.m(String.valueOf(r45).length() + 24, "Unsupported value kind: ", r45);
        return null;
    L15:
        return new zzip(r4.zzg());
    L24:
        return new zzim(r4.zze());
    L26:
        return new zzin(r4.zzd());
    L28:
        return new zzil(r4.zzc());
    }

    public abstract boolean zza();

    public abstract int zzb();

    public abstract double zzc();

    public abstract int zzd();

    public abstract zzml zze();

    public abstract long zzf();

    public abstract String zzg();
}
