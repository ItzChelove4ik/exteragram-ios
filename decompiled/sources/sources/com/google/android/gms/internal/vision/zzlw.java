package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
final class zzlw extends zzlu<zzlx, zzlx> {
    public zzlw() {
    }

    /* renamed from: zza, reason: avoid collision after fix types in other method */
    private static void zza2(Object r0, zzlx r1) {
        ((zzjb) r0).zzb = r1;
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ zzlx zza() {
        return zzlx.zzb();
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zza(zzlx r1, int r2, long r3) {
        r1.zza(r2 << 3, Long.valueOf(r3));
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zza(zzlx r1, int r2, zzht r3) {
        r1.zza((r2 << 3) | 2, r3);
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zza(zzlx r1, zzmr r2) {
        r1.zzb(r2);
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* bridge */ /* synthetic */ void zza(Object r1, zzlx r2) {
        zza2(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ zzlx zzb(Object r1) {
        return ((zzjb) r1).zzb;
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zzb(zzlx r1, zzmr r2) {
        r1.zza(r2);
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ void zzb(Object r1, zzlx r2) {
        zza2(r1, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ zzlx zzc(zzlx r1, zzlx r2) {
        zzlx r12 = r1;
        zzlx r22 = r2;
        if (r22.equals(zzlx.zza()) == false) goto L6;
        return r12;
    L6:
        return zzlx.zza(r12, r22);
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final void zzd(Object r1) {
        ((zzjb) r1).zzb.zzc();
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ int zze(zzlx r1) {
        return r1.zzd();
    }

    @Override // com.google.android.gms.internal.vision.zzlu
    public final /* synthetic */ int zzf(zzlx r1) {
        return r1.zze();
    }
}
