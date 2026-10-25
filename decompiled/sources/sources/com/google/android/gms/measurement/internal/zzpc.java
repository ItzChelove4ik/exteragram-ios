package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzpc {
    com.google.android.gms.internal.measurement.zzid zza;
    List zzb;
    List zzc;
    long zzd;
    final /* synthetic */ zzpg zze;

    public /* synthetic */ zzpc(zzpg r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zze = r1;
    }

    private static final long zzb(com.google.android.gms.internal.measurement.zzhs r4) {
        return ((r4.zzf() / 1000) / 60) / 60;
    }

    public final boolean zza(long r8, com.google.android.gms.internal.measurement.zzhs r10) {
        Preconditions.checkNotNull(r10);
        if (this.zzc != null) goto L6;
        this.zzc = new ArrayList();
    L6:
        if (this.zzb != null) goto L9;
        this.zzb = new ArrayList();
    L9:
        if (this.zzc.isEmpty() == false) goto L11;
    L13:
        long r2 = this.zzd + r10.zzcq();
        zzpg r0 = this.zze;
        String r6 = null;
        if (r0.zzd().zzp(null, zzfy.zzaY) == true) goto L16;
        r0.zzd();
        if (r2 < zzal.zzG()) goto L24;
        return false;
    L24:
        this.zzd = r2;
        this.zzc.add(r10);
        this.zzb.add(Long.valueOf(r8));
        com.google.android.gms.internal.measurement.zzid r82 = this.zza;
        if (r82 == null) goto L29;
        r6 = r82.zzA();
    L29:
        if (this.zzc.size() < Math.max(1, r0.zzd().zzm(r6, zzfy.zzj))) goto L31;
        return false;
    L31:
        return true;
    L16:
        if (this.zzc.isEmpty() == true) goto L24;
        r0.zzd();
        if (r2 < zzal.zzG()) goto L24;
        return false;
    L11:
        if (zzb((com.google.android.gms.internal.measurement.zzhs) this.zzc.get(0)) == zzb(r10)) goto L13;
        return false;
    }
}
