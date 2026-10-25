package com.google.android.gms.internal.measurement;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;

/* loaded from: classes4.dex */
final class zzafg implements zzafp {
    private final zzafc zza;
    private final zzafz zzb;
    private final boolean zzc;
    private final zzadg zzd;

    private zzafg(zzafz r1, zzadg r2, zzafc r3) {
        this.zzb = r1;
        this.zzc = false;
        this.zzd = r2;
        this.zza = r3;
    }

    public static zzafg zzh(zzafz r1, zzadg r2, zzafc r3) {
        return new zzafg(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final Object zza() {
        zzafc r1 = this.zza;
        if ((r1 instanceof zzadu) == false) goto L7;
        return ((zzadu) r1).zzck();
    L7:
        return r1.zzcI().zzbg();
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final boolean zzb(Object r2, Object r3) {
        if (((zzadu) r2).zzc.equals(((zzadu) r3).zzc) == true) goto L7;
        return false;
    L7:
        if (this.zzc == true) goto L10;
        return true;
    L10:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final int zzc(Object r2) {
        int r0 = ((zzadu) r2).zzc.hashCode();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final void zzd(Object r2, Object r3) {
        zzafq.zzE(this.zzb, r2, r3);
        if (this.zzc == false) goto L6;
        zzafq.zzD(this.zzd, r2, r3);
        return;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final int zze(Object r2) {
        int r0 = ((zzadu) r2).zzc.zzh();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final void zzf(Object r1, zzago r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final void zzg(Object r1, zzafo r2, zzadf r3) {
        this.zzb.zzh(r1);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final void zzj(Object r1, byte[] r2, int r3, int r4, zzacg r5) {
        zzadu r0 = (zzadu) r1;
        if (r0.zzc != zzaga.zza()) goto L6;
        r0.zzc = zzaga.zzb();
    L6:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final void zzk(Object r2) {
        this.zzb.zzj(r2);
        this.zzd.zza(r2);
    }

    @Override // com.google.android.gms.internal.measurement.zzafp
    public final boolean zzl(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }
}
