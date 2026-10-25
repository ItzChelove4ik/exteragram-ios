package com.google.android.gms.internal.measurement;

import java.util.Arrays;
import java.util.Comparator;

/* loaded from: classes4.dex */
final class zzaz implements Comparator {
    final /* synthetic */ zzai zza;
    final /* synthetic */ zzg zzb;

    public zzaz(zzai r1, zzg r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(Object r5, Object r6) {
        zzao r52 = (zzao) r5;
        zzao r62 = (zzao) r6;
        if ((r52 instanceof zzat) == false) goto L9;
        if ((r62 instanceof zzat) == true) goto L7;
        return 1;
    L7:
        return 0;
    L9:
        if ((r62 instanceof zzat) == false) goto L12;
        return -1;
    L12:
        zzai r0 = this.zza;
        if (r0 != null) goto L17;
        return r52.zzc().compareTo(r62.zzc());
    L17:
        return (int) zzh.zzi(r0.zza(this.zzb, Arrays.asList(new zzao[]{r52, r62})).zzd().doubleValue());
    }
}
