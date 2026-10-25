package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import java.util.Objects;

/* loaded from: classes5.dex */
final class zzoa {
    protected long zza;
    protected long zzb;
    final /* synthetic */ zzoc zzc;
    private final zzaz zzd;

    public zzoa(zzoc r3) {
        Objects.requireNonNull(r3);
        this.zzc = r3;
        this.zzd = new zznz(this, r3.zzu);
        long r0 = r3.zzu.zzba().elapsedRealtime();
        this.zza = r0;
        this.zzb = r0;
    }

    public final void zza(long r2) {
        this.zzc.zzg();
        this.zzd.zzd();
        this.zza = r2;
        this.zzb = r2;
    }

    public final void zzb(long r1) {
        this.zzd.zzd();
    }

    public final void zzc() {
        this.zzd.zzd();
        long r0 = this.zzc.zzu.zzba().elapsedRealtime();
        this.zza = r0;
        this.zzb = r0;
    }

    public final boolean zzd(boolean r7, boolean r8, long r9) {
        zzoc r0 = this.zzc;
        r0.zzg();
        r0.zzb();
        if (r0.zzu.zzB() == false) goto L5;
        zzic r1 = r0.zzu;
        r1.zzd().zzk.zzb(r1.zzba().currentTimeMillis());
    L5:
        long r12 = r9 - this.zza;
        if (r7 == false) goto L8;
    L12:
        if (r8 == true) goto L14;
        r12 = r9 - this.zzb;
        this.zzb = r9;
    L14:
        zzic r72 = r0.zzu;
        r72.zzaW().zzk().zzb("Recording user engagement, ms", Long.valueOf(r12));
        Bundle r3 = new Bundle();
        r3.putLong("_et", r12);
        boolean r73 = !r72.zzc().zzv();
        zzic r02 = r0.zzu;
        zzpp.zzay(r02.zzs().zzh(r73), r3, true);
        if (r8 == true) goto L17;
        r02.zzj().zzE("auto", "_e", r3);
    L17:
        this.zza = r9;
        zzaz r6 = this.zzd;
        r6.zzd();
        r6.zzb(((Long) zzfy.zzap.zzb(null)).longValue());
        return true;
    L8:
        if (r12 >= 1000) goto L12;
        r0.zzu.zzaW().zzk().zzb("Screen exposed for less than 1000 ms. Event not sent. time", Long.valueOf(r12));
        return false;
    }
}
