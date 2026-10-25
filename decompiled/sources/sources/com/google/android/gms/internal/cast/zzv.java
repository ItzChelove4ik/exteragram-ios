package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.MediaStatus;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzv implements com.google.android.gms.cast.framework.zzs {
    final /* synthetic */ zzy zza;

    public /* synthetic */ zzv(zzy r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.zzs
    public final void zza() {
        zzcs r1 = new zzcs(new zzcr(3));
        this.zza.zza(r1);
    }

    @Override // com.google.android.gms.cast.framework.zzs
    public final void zzb(String r2, long r3, int r5, long r6, long r8) {
        zzaa r1 = this.zza.zzb();
        zzcp r0 = new zzcp(r2);
        r0.zza(r3);
        r0.zzb(r5);
        r0.zzc(r6);
        r0.zzd(r8);
        r1.zzd(new zzcq(r0));
    }

    @Override // com.google.android.gms.cast.framework.zzs
    public final void zzc(MediaStatus r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        this.zza.zzb().zze(new zzt(new zzs(r2)));
    }

    @Override // com.google.android.gms.cast.framework.zzs
    public final void zzd() {
        this.zza.zzb().zzf();
    }
}
