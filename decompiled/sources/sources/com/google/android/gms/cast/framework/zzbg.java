package com.google.android.gms.cast.framework;

import android.os.Bundle;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzbg extends zzbc {
    final /* synthetic */ Session zza;

    public /* synthetic */ zzbg(Session r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.zzbd
    public final IObjectWrapper zzb() {
        return ObjectWrapper.wrap(this.zza);
    }

    @Override // com.google.android.gms.cast.framework.zzbd
    public final void zzc(Bundle r1) {
        this.zza.onStarting(r1);
    }

    @Override // com.google.android.gms.cast.framework.zzbd
    public final void zzd(Bundle r1) {
        this.zza.start(r1);
    }

    @Override // com.google.android.gms.cast.framework.zzbd
    public final void zze(Bundle r1) {
        this.zza.onResuming(r1);
    }

    @Override // com.google.android.gms.cast.framework.zzbd
    public final void zzf(Bundle r1) {
        this.zza.resume(r1);
    }

    @Override // com.google.android.gms.cast.framework.zzbd
    public final void zzg(boolean r1) {
        this.zza.end(r1);
    }

    @Override // com.google.android.gms.cast.framework.zzbd
    public final void zzh(Bundle r1) {
        this.zza.zza(r1);
    }

    @Override // com.google.android.gms.cast.framework.zzbd
    public final long zzi() {
        return this.zza.getSessionRemainingTimeMs();
    }
}
