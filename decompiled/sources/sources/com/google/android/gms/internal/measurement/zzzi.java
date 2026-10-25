package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
final class zzzi extends zzzj {
    static final zzzi zza = null;

    static {
        zza = new zzzi();
    }

    private zzzi() {
    }

    @Override // com.google.android.gms.internal.measurement.zzzj
    public final int zza() {
        return 0;
    }

    @Override // com.google.android.gms.internal.measurement.zzzj
    public final zzyl zzb(int r1) {
        throw new IndexOutOfBoundsException("cannot read from empty metadata");
    }

    @Override // com.google.android.gms.internal.measurement.zzzj
    public final Object zzc(int r1) {
        throw new IndexOutOfBoundsException("cannot read from empty metadata");
    }

    @Override // com.google.android.gms.internal.measurement.zzzj
    public final Object zzd(zzyl r1) {
        return null;
    }
}
