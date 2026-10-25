package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes4.dex */
final class zzzn extends zzzq {
    private final Map zza;
    private final Map zzb;
    private final zzzp zzc;
    private final zzzo zzd;

    public /* synthetic */ zzzn(zzzm r3, byte[] r4) {
        HashMap r42 = new HashMap();
        this.zza = r42;
        HashMap r0 = new HashMap();
        this.zzb = r0;
        r42.putAll(r3.zzd());
        r0.putAll(r3.zze());
        this.zzc = r3.zzf();
        this.zzd = r3.zzg();
    }

    @Override // com.google.android.gms.internal.measurement.zzzq
    public final void zza(zzyl r2, Object r3, Object r4) {
        zzzp r0 = (zzzp) this.zza.get(r2);
        if (r0 == null) goto L6;
        r0.zza(r2, r3, r4);
        return;
    L6:
        this.zzc.zza(r2, r3, r4);
    }

    @Override // com.google.android.gms.internal.measurement.zzzq
    public final void zzb(zzyl r3, Iterator r4, Object r5) {
        zzzo r0 = (zzzo) this.zzb.get(r3);
        if (r0 == null) goto L6;
        r0.zza(r3, r4, r5);
        return;
    L6:
        zzzo r02 = this.zzd;
        if (r02 == null) goto L14;
        if (this.zza.containsKey(r3) == true) goto L14;
        r02.zza(r3, r4, r5);
        return;
    L14:
        if (r4.hasNext() == false) goto L16;
        zza(r3, r4.next(), r5);
        goto L14
    }
}
