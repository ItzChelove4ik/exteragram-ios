package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzk extends zzai {
    private final zzab zza;

    public zzk(zzab r2) {
        super("internal.eventLogger");
        this.zza = r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg r5, List r6) {
        zzh.zza(this.zzd, 3, r6);
        String r0 = r5.zza((zzao) r6.get(0)).zzc();
        long r1 = (long) zzh.zzi(r5.zza((zzao) r6.get(1)).zzd().doubleValue());
        zzao r52 = r5.zza((zzao) r6.get(2));
        if ((r52 instanceof zzal) == false) goto L5;
        Map r53 = zzh.zzk((zzal) r52);
    L6:
        this.zza.zze(r0, r1, r53);
        return zzao.zzf;
    L5:
        r53 = new HashMap();
        goto L6
    }
}
