package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
final class zzib {
    private final zzii zza;
    private final byte[] zzb;

    private zzib(int r1) {
        byte[] r12 = new byte[r1];
        this.zzb = r12;
        this.zza = zzii.zza(r12);
    }

    public /* synthetic */ zzib(int r1, zzhs r2) {
        this(r1);
    }

    public final zzht zza() {
        this.zza.zzb();
        return new zzid(this.zzb);
    }

    public final zzii zzb() {
        return this.zza;
    }
}
