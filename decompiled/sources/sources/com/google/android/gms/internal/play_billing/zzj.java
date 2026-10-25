package com.google.android.gms.internal.play_billing;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* loaded from: classes5.dex */
final class zzj extends zzd {
    final AtomicReferenceFieldUpdater zza;
    final AtomicReferenceFieldUpdater zzb;
    final AtomicReferenceFieldUpdater zzc;
    final AtomicReferenceFieldUpdater zzd;
    final AtomicReferenceFieldUpdater zze;

    public zzj(AtomicReferenceFieldUpdater r2, AtomicReferenceFieldUpdater r3, AtomicReferenceFieldUpdater r4, AtomicReferenceFieldUpdater r5, AtomicReferenceFieldUpdater r6) {
        super(null);
        this.zza = r2;
        this.zzb = r3;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final void zza(zzm r1, zzm r2) {
        this.zzb.lazySet(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final void zzb(zzm r1, Thread r2) {
        this.zza.lazySet(r1, r2);
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final boolean zzc(zzo r1, zzh r2, zzh r3) {
        return zzi.zza(this.zzd, r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final boolean zzd(zzo r1, Object r2, Object r3) {
        return zzi.zza(this.zze, r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.play_billing.zzd
    public final boolean zze(zzo r1, zzm r2, zzm r3) {
        return zzi.zza(this.zzc, r1, r2, r3);
    }
}
