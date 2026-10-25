package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
public abstract class zzu {
    public static zzcz zza(zzr r3) {
        zzp r0 = new zzp();
        zzt r1 = new zzt(r0);
        r0.zzb = r1;
        r0.zza = r3.getClass();
        r0.zza = r3.zza(r0);     // Catch: Exception -> L5
        return r1;
    L5:
        e = move-exception;
        r1.zzc(e);
        return r1;
    }
}
