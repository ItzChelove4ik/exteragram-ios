package com.google.android.gms.internal.play_billing;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;

/* loaded from: classes5.dex */
final class zzgp implements zzgv {
    private final zzgl zza;
    private final zzhh zzb;
    private final boolean zzc;
    private final zzev zzd;

    private zzgp(zzhh r1, zzev r2, zzgl r3) {
        this.zzb = r1;
        this.zzc = false;
        this.zzd = r2;
        this.zza = r3;
    }

    public static zzgp zzc(zzhh r1, zzev r2, zzgl r3) {
        return new zzgp(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final int zza(Object r2) {
        int r0 = ((zzfi) r2).zzc.zzb();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final int zzb(Object r2) {
        int r0 = ((zzfi) r2).zzc.hashCode();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final Object zze() {
        zzgl r1 = this.zza;
        if ((r1 instanceof zzfi) == false) goto L7;
        return ((zzfi) r1).zzo();
    L7:
        return r1.zzK().zzg();
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final void zzf(Object r2) {
        this.zzb.zzb(r2);
        this.zzd.zza(r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final void zzg(Object r2, Object r3) {
        zzgx.zzp(this.zzb, r2, r3);
        if (this.zzc == false) goto L6;
        zzgx.zzo(this.zzd, r2, r3);
        return;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final void zzh(Object r1, byte[] r2, int r3, int r4, zzdw r5) {
        zzfi r0 = (zzfi) r1;
        if (r0.zzc != zzhi.zzc()) goto L6;
        r0.zzc = zzhi.zzf();
    L6:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final void zzi(Object r1, zzhu r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final boolean zzj(Object r2, Object r3) {
        if (((zzfi) r2).zzc.equals(((zzfi) r3).zzc) == true) goto L7;
        return false;
    L7:
        if (this.zzc == true) goto L10;
        return true;
    L10:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.gms.internal.play_billing.zzgv
    public final boolean zzk(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }
}
