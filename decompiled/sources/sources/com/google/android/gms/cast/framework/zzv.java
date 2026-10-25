package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbm;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzv extends zzae {
    final /* synthetic */ CastSession zza;

    public /* synthetic */ zzv(CastSession r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // com.google.android.gms.cast.framework.zzaf
    public final void zzb(String r3, String r4) {
        CastSession r0 = this.zza;
        if (r0.zzj() != null) goto L5;
        return;
    L5:
        if (r0.zzj().zza() == false) goto L9;
        ((zzbm) r0.zzj()).zzs(r3, r4, null).addOnCompleteListener(new zzu(this));
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaf
    public final void zzc(String r3, LaunchOptions r4) {
        CastSession r0 = this.zza;
        if (r0.zzj() != null) goto L5;
        return;
    L5:
        if (r0.zzj().zza() == false) goto L9;
        r0.zzj().zzg(r3, r4).addOnCompleteListener(new zzt(this));
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaf
    public final void zzd(String r2) {
        CastSession r1 = this.zza;
        if (r1.zzj() != null) goto L5;
        return;
    L5:
        if (r1.zzj().zza() == false) goto L9;
        r1.zzj().zzh(r2);
        return;
    }

    @Override // com.google.android.gms.cast.framework.zzaf
    public final void zze(int r1) {
        this.zza.zzf(r1);
    }
}
