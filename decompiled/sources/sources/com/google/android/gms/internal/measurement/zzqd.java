package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzqd implements zzpm {
    final /* synthetic */ zzqe zza;
    private final zzqm zzb;

    public zzqd(zzqe r1, zzqm r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzpm
    public final void zza(zzpl r5) {
        Iterator r0 = this.zza.zzc().iterator();
        boolean r1 = false;
    L4:
        if (r0.hasNext() == false) goto L9;
        if (((zzqc) r0.next()).zza(r5.zza()) == false) goto L4;
        if (r1 == true) goto L4;
        this.zzb.zza();
        r1 = true;
        goto L4
    }

    @Override // com.google.android.gms.internal.measurement.zzpm
    public final void zzb(Throwable r1) {
    }
}
