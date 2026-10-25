package com.google.android.recaptcha.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;

/* loaded from: classes5.dex */
final class zzahb implements zzahn {
    private final zzagx zza;
    private final zzahz zzb;
    private final boolean zzc;
    private final zzafi zzd;

    private zzahb(zzahz r1, zzafi r2, zzagx r3) {
        this.zzb = r1;
        this.zzc = false;
        this.zzd = r2;
        this.zza = r3;
    }

    public static zzahb zzh(zzahz r1, zzafi r2, zzagx r3) {
        return new zzahb(r1, r2, r3);
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final Object zza() {
        zzagx r1 = this.zza;
        if ((r1 instanceof zzafv) == false) goto L7;
        return ((zzafv) r1).zzC();
    L7:
        return r1.zzX().zzr();
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final boolean zzb(Object r2, Object r3) {
        if (((zzafv) r2).zzc.equals(((zzafv) r3).zzc) == true) goto L7;
        return false;
    L7:
        if (this.zzc == true) goto L10;
        return true;
    L10:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final int zzc(Object r2) {
        int r0 = ((zzafv) r2).zzc.hashCode();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzd(Object r2, Object r3) {
        zzaho.zzD(this.zzb, r2, r3);
        if (this.zzc == false) goto L6;
        zzaho.zzC(this.zzd, r2, r3);
        return;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final int zze(Object r2) {
        int r0 = ((zzafv) r2).zzc.zzh();
        if (this.zzc == true) goto L5;
        return r0;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzf(Object r1, zzafb r2) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzg(Object r1, zzaew r2, zzafh r3) {
        zzaib.zzj(r1);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzj(Object r1, byte[] r2, int r3, int r4, zzaeg r5) {
        zzafv r0 = (zzafv) r1;
        if (r0.zzc != zzaia.zza()) goto L6;
        r0.zzc = zzaia.zzb();
    L6:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final void zzk(Object r1) {
        ((zzafv) r1).zzc.zzd();
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }

    @Override // com.google.android.recaptcha.internal.zzahn
    public final boolean zzl(Object r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        throw null;
    }
}
