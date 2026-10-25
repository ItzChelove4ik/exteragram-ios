package com.google.android.gms.internal.location;

/* loaded from: classes4.dex */
final class zzes extends zzeq {
    private final zzeu zza;

    public zzes(zzeu r2, int r3) {
        super(r2.size(), r3);
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.location.zzeq
    public final Object zza(int r1) {
        return this.zza.get(r1);
    }
}
