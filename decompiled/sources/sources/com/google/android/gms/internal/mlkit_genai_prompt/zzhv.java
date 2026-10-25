package com.google.android.gms.internal.mlkit_genai_prompt;

import java.util.List;

/* loaded from: classes4.dex */
public abstract class zzhv {
    public zzhv() {
    }

    public static zzhv zzf(String r6, float r7, int r8, zzho r9, List r10) {
        if (r10 != null) goto L6;
        zzml r102 = zzml.zzk();
    L8:
        return new zzhg(r6, r7, r8, r9, r102);
    L6:
        r102 = zzml.zzq(r10);
        goto L8
    }

    public abstract String zza();

    public abstract float zzb();

    public abstract int zzc();

    public abstract zzho zzd();

    public abstract zzml zze();
}
