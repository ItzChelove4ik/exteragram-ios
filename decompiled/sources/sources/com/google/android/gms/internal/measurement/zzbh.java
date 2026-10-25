package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzbh extends zzav {
    public zzbh() {
        this.zza.add(zzbk.zza);
        this.zza.add(zzbk.zzv);
        this.zza.add(zzbk.zzS);
        this.zza.add(zzbk.zzT);
        this.zza.add(zzbk.zzU);
        this.zza.add(zzbk.zzaa);
        this.zza.add(zzbk.zzab);
        this.zza.add(zzbk.zzad);
        this.zza.add(zzbk.zzae);
        this.zza.add(zzbk.zzah);
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String r6, zzg r7, List r8) {
        zzbk r0 = zzbk.zza;
        int r02 = zzh.zze(r6).ordinal();
        if (r02 != 0) goto L5;
        zzh.zza(zzbk.zza.name(), 2, r8);
        zzao r5 = r7.zza((zzao) r8.get(0));
        zzao r62 = r7.zza((zzao) r8.get(1));
        if ((r5 instanceof zzak) == false) goto L36;
    L44:
        String r52 = r5.zzc();
        String r63 = r62.zzc();
        return new zzas(String.valueOf(r52).concat(String.valueOf(r63)));
    L36:
        if ((r5 instanceof zzas) == true) goto L44;
        if ((r62 instanceof zzak) == true) goto L44;
        if ((r62 instanceof zzas) == true) goto L44;
        return new zzah(Double.valueOf(r5.zzd().doubleValue() + r62.zzd().doubleValue()));
    L5:
        if (r02 != 21) goto L7;
        zzh.zza(zzbk.zzv.name(), 2, r8);
        return new zzah(Double.valueOf(r7.zza((zzao) r8.get(0)).zzd().doubleValue() / r7.zza((zzao) r8.get(1)).zzd().doubleValue()));
    L7:
        if (r02 != 59) goto L9;
        zzh.zza(zzbk.zzah.name(), 2, r8);
        zzao r53 = r7.zza((zzao) r8.get(0));
        zzah r72 = new zzah(Double.valueOf(-r7.zza((zzao) r8.get(1)).zzd().doubleValue()));
        return new zzah(Double.valueOf(r53.zzd().doubleValue() + r72.zzd().doubleValue()));
    L9:
        if (r02 != 52) goto L11;
    L27:
        zzh.zza(r6, 2, r8);
        zzao r54 = r7.zza((zzao) r8.get(0));
        r7.zza((zzao) r8.get(1));
        return r54;
    L11:
        if (r02 == 53) goto L27;
        if (r02 != 55) goto L15;
    L25:
        zzh.zza(r6, 1, r8);
        return r7.zza((zzao) r8.get(0));
    L15:
        if (r02 == 56) goto L25;
        switch(r02) {
            case 44: goto L23;
            case 45: goto L21;
            case 46: goto L19;
            default: goto L18;
        };
    L19:
        zzh.zza(zzbk.zzU.name(), 1, r8);
        return new zzah(Double.valueOf(-r7.zza((zzao) r8.get(0)).zzd().doubleValue()));
    L21:
        zzh.zza(zzbk.zzT.name(), 2, r8);
        return new zzah(Double.valueOf(r7.zza((zzao) r8.get(0)).zzd().doubleValue() * r7.zza((zzao) r8.get(1)).zzd().doubleValue()));
    L23:
        zzh.zza(zzbk.zzS.name(), 2, r8);
        return new zzah(Double.valueOf(r7.zza((zzao) r8.get(0)).zzd().doubleValue() % r7.zza((zzao) r8.get(1)).zzd().doubleValue()));
    L18:
        return super.zzb(r6);
    }
}
