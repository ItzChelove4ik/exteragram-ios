package com.google.android.recaptcha.internal;

/* loaded from: classes5.dex */
final class zzaib extends zzahz {
    public zzaib() {
    }

    public static final void zzi(zzaia r0, int r1, long r2) {
        r0.zzk(r1 << 3, Long.valueOf(r2));
    }

    public static final zzaia zzj(Object r2) {
        zzafv r22 = (zzafv) r2;
        zzaia r0 = r22.zzc;
        if (r0 != zzaia.zza()) goto L6;
        zzaia r02 = zzaia.zzb();
        r22.zzc = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // com.google.android.recaptcha.internal.zzahz
    public final /* bridge */ /* synthetic */ void zza(Object r1, int r2, long r3) {
        zzi((zzaia) r1, r2, r3);
    }

    @Override // com.google.android.recaptcha.internal.zzahz
    public final /* bridge */ /* synthetic */ void zzb(Object r1, int r2, int r3) {
        ((zzaia) r1).zzk((r2 << 3) | 5, Integer.valueOf(r3));
    }

    @Override // com.google.android.recaptcha.internal.zzahz
    public final /* bridge */ /* synthetic */ void zzc(Object r1, int r2, long r3) {
        ((zzaia) r1).zzk((r2 << 3) | 1, Long.valueOf(r3));
    }

    @Override // com.google.android.recaptcha.internal.zzahz
    public final /* bridge */ /* synthetic */ void zzd(Object r1, int r2, zzaer r3) {
        ((zzaia) r1).zzk((r2 << 3) | 2, r3);
    }

    @Override // com.google.android.recaptcha.internal.zzahz
    public final /* bridge */ /* synthetic */ void zze(Object r1, int r2, Object r3) {
        ((zzaia) r1).zzk((r2 << 3) | 3, (zzaia) r3);
    }

    @Override // com.google.android.recaptcha.internal.zzahz
    public final /* synthetic */ Object zzf() {
        return zzaia.zzb();
    }

    @Override // com.google.android.recaptcha.internal.zzahz
    public final /* synthetic */ Object zzg(Object r1) {
        zzaia r12 = (zzaia) r1;
        r12.zzd();
        return r12;
    }
}
