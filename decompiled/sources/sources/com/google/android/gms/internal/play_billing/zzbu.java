package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
final class zzbu {
    private final Object zza;
    private final Object zzb;
    private final Object zzc;

    public zzbu(Object r1, Object r2, Object r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public final IllegalArgumentException zza() {
        Object r0 = this.zzc;
        Object r1 = this.zzb;
        Object r6 = this.zza;
        return new IllegalArgumentException("Multiple entries with same key: " + String.valueOf(r6) + "=" + String.valueOf(r1) + " and " + String.valueOf(r6) + "=" + String.valueOf(r0));
    }
}
