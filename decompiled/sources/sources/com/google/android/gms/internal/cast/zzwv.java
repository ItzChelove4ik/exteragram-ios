package com.google.android.gms.internal.cast;

import java.util.Objects;
import java.util.concurrent.Callable;

/* loaded from: classes4.dex */
final class zzwv extends zzwm {
    final /* synthetic */ zzww zza;
    private final Callable zzb;

    public zzwv(zzww r1, Callable r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        r2.getClass();
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.cast.zzwm
    public final boolean zza() {
        return this.zza.isDone();
    }

    @Override // com.google.android.gms.internal.cast.zzwm
    public final Object zzb() {
        return this.zzb.call();
    }

    @Override // com.google.android.gms.internal.cast.zzwm
    public final void zzc(Object r1) {
        this.zza.zzc(r1);
    }

    @Override // com.google.android.gms.internal.cast.zzwm
    public final void zzd(Throwable r1) {
        this.zza.zzd(r1);
    }

    @Override // com.google.android.gms.internal.cast.zzwm
    public final String zzf() {
        return this.zzb.toString();
    }
}
