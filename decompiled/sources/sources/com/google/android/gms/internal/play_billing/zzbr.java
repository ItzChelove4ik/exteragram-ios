package com.google.android.gms.internal.play_billing;

/* loaded from: classes5.dex */
final class zzbr extends zzbn {
    private final zzbt zza;

    public zzbr(zzbt r2, int r3) {
        super(r2.size(), r3);
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.play_billing.zzbn
    public final Object zza(int r1) {
        return this.zza.get(r1);
    }
}
