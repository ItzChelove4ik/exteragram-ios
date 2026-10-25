package com.google.android.gms.internal.measurement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzw extends zzai {
    final Map zza;
    private final zzj zzb;

    public zzw(zzj r2) {
        super("require");
        this.zza = new HashMap();
        this.zzb = r2;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg r3, List r4) {
        zzh.zza("require", 1, r4);
        String r32 = r3.zza((zzao) r4.get(0)).zzc();
        Map r42 = this.zza;
        if (r42.containsKey(r32) == true) goto L5;
        Map r43 = this.zzb.zza;
        if (r43.containsKey(r32) == true) goto L18;
        zzao r44 = zzao.zzf;
    L15:
        if ((r44 instanceof zzai) == false) goto L17;
        this.zza.put(r32, (zzai) r44);
    L17:
        return r44;
    L18:
        r44 = (zzao) ((Callable) r43.get(r32)).call();     // Catch: Exception -> L11
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("Failed to create API implementation: ".concat(String.valueOf(r32)));
        return null;
    L5:
        return (zzao) r42.get(r32);
    }
}
