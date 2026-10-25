package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzbb extends zzav {
    public zzbb() {
        this.zza.add(zzbk.zzb);
        this.zza.add(zzbk.zzV);
        this.zza.add(zzbk.zzY);
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String r6, zzg r7, List r8) {
        zzbk r0 = zzbk.zza;
        int r02 = zzh.zze(r6).ordinal();
        if (r02 != 1) goto L5;
        zzh.zza(zzbk.zzb.name(), 2, r8);
        zzao r5 = r7.zza((zzao) r8.get(0));
        if (r5.zze().booleanValue() == true) goto L19;
        return r5;
    L19:
        return r7.zza((zzao) r8.get(1));
    L5:
        if (r02 != 47) goto L7;
        zzh.zza(zzbk.zzV.name(), 1, r8);
        return new zzaf(Boolean.valueOf(!r7.zza((zzao) r8.get(0)).zze().booleanValue()));
    L7:
        if (r02 != 50) goto L9;
        zzh.zza(zzbk.zzY.name(), 2, r8);
        zzao r52 = r7.zza((zzao) r8.get(0));
        if (r52.zze().booleanValue() == false) goto L13;
        return r52;
    L13:
        return r7.zza((zzao) r8.get(1));
    L9:
        return super.zzb(r6);
    }
}
