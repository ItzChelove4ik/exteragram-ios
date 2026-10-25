package com.google.android.gms.internal.cast;

import android.app.Activity;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzbj implements com.google.android.gms.cast.framework.internal.featurehighlight.zzg {
    final /* synthetic */ Activity zza;
    final /* synthetic */ com.google.android.gms.cast.framework.internal.featurehighlight.zzh zzb;
    final /* synthetic */ zzbk zzc;

    public zzbj(zzbk r1, Activity r2, com.google.android.gms.cast.framework.internal.featurehighlight.zzh r3) {
        this.zza = r2;
        this.zzb = r3;
        Objects.requireNonNull(r1);
        this.zzc = r1;
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzg
    public final void zza() {
        if (this.zzc.zzc() == true) goto L5;
        return;
    L5:
        Activity r0 = this.zza;
        com.google.android.gms.cast.framework.zzbe.zza(r0);
        this.zzb.zze(new zzbi(this, r0));
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzg
    public final void zzb() {
        if (this.zzc.zzc() == true) goto L5;
        return;
    L5:
        Activity r0 = this.zza;
        com.google.android.gms.cast.framework.zzbe.zza(r0);
        this.zzb.zzd(new zzbh(this, r0));
    }
}
