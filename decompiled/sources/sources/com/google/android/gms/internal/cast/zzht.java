package com.google.android.gms.internal.cast;

/* loaded from: classes4.dex */
final class zzht extends zzhl {
    private final zzhv zza;

    public zzht(zzhv r2, int r3) {
        super(r2.size(), r3);
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.cast.zzhl
    public final Object zza(int r1) {
        return this.zza.get(r1);
    }
}
