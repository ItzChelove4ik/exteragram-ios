package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzau extends zzav {
    public zzau() {
        this.zza.add(zzbk.zze);
        this.zza.add(zzbk.zzf);
        this.zza.add(zzbk.zzg);
        this.zza.add(zzbk.zzh);
        this.zza.add(zzbk.zzi);
        this.zza.add(zzbk.zzj);
        this.zza.add(zzbk.zzk);
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String r7, zzg r8, List r9) {
        zzbk r0 = zzbk.zza;
        switch(zzh.zze(r7).ordinal()) {
            case 4: goto L18;
            case 5: goto L16;
            case 6: goto L14;
            case 7: goto L12;
            case 8: goto L10;
            case 9: goto L8;
            case 10: goto L6;
            default: goto L5;
        };
    L6:
        zzh.zza(zzbk.zzk.name(), 2, r9);
        return new zzah(Double.valueOf(zzh.zzg(r8.zza((zzao) r9.get(0)).zzd().doubleValue()) ^ zzh.zzg(r8.zza((zzao) r9.get(1)).zzd().doubleValue())));
    L8:
        zzh.zza(zzbk.zzj.name(), 2, r9);
        return new zzah(Double.valueOf(zzh.zzh(r8.zza((zzao) r9.get(0)).zzd().doubleValue()) >>> ((int) (zzh.zzh(r8.zza((zzao) r9.get(1)).zzd().doubleValue()) & 31))));
    L10:
        zzh.zza(zzbk.zzi.name(), 2, r9);
        return new zzah(Double.valueOf(zzh.zzg(r8.zza((zzao) r9.get(0)).zzd().doubleValue()) >> ((int) (zzh.zzh(r8.zza((zzao) r9.get(1)).zzd().doubleValue()) & 31))));
    L12:
        zzh.zza(zzbk.zzh.name(), 2, r9);
        return new zzah(Double.valueOf(zzh.zzg(r8.zza((zzao) r9.get(0)).zzd().doubleValue()) | zzh.zzg(r8.zza((zzao) r9.get(1)).zzd().doubleValue())));
    L14:
        zzh.zza(zzbk.zzg.name(), 1, r9);
        return new zzah(Double.valueOf(~zzh.zzg(r8.zza((zzao) r9.get(0)).zzd().doubleValue())));
    L16:
        zzh.zza(zzbk.zzf.name(), 2, r9);
        return new zzah(Double.valueOf(zzh.zzg(r8.zza((zzao) r9.get(0)).zzd().doubleValue()) << ((int) (zzh.zzh(r8.zza((zzao) r9.get(1)).zzd().doubleValue()) & 31))));
    L18:
        zzh.zza(zzbk.zze.name(), 2, r9);
        return new zzah(Double.valueOf(zzh.zzg(r8.zza((zzao) r9.get(0)).zzd().doubleValue()) & zzh.zzg(r8.zza((zzao) r9.get(1)).zzd().doubleValue())));
    L5:
        return super.zzb(r7);
    }
}
