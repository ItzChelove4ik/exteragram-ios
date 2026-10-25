package com.google.android.gms.internal.measurement;

import java.util.List;

/* loaded from: classes4.dex */
public final class zzax extends zzav {
    public zzax() {
        this.zza.add(zzbk.zzx);
        this.zza.add(zzbk.zzL);
        this.zza.add(zzbk.zzM);
        this.zza.add(zzbk.zzN);
        this.zza.add(zzbk.zzO);
        this.zza.add(zzbk.zzQ);
        this.zza.add(zzbk.zzR);
        this.zza.add(zzbk.zzW);
    }

    private static boolean zzc(zzao r8, zzao r9) {
        if ((r8 instanceof zzak) == false) goto L6;
        r8 = new zzas(r8.zzc());
    L6:
        if ((r9 instanceof zzak) == false) goto L9;
        r9 = new zzas(r9.zzc());
    L9:
        if ((r8 instanceof zzas) == true) goto L11;
    L17:
        double r3 = r8.zzd().doubleValue();
        double r82 = r9.zzd().doubleValue();
        if (Double.isNaN(r3) == false) goto L20;
    L33:
        return false;
    L20:
        if (Double.isNaN(r82) == true) goto L33;
        if (r3 == 0.0d) goto L25;
    L26:
        if (r3 != 0.0d) goto L31;
        if (r82 != 0.0d) goto L31;
    L29:
        return false;
    L31:
        if (Double.compare(r3, r82) >= 0) goto L33;
        return true;
    L25:
        if (r82 == 0.0d) goto L29;
    L11:
        if ((r9 instanceof zzas) == false) goto L17;
        if (r8.zzc().compareTo(r9.zzc()) >= 0) goto L16;
        return true;
    L16:
        return false;
    }

    private static boolean zzd(zzao r5, zzao r6) {
        if (r5.getClass().equals(r6.getClass()) == false) goto L33;
        if ((r5 instanceof zzat) == false) goto L7;
    L31:
        return true;
    L7:
        if ((r5 instanceof zzam) == true) goto L31;
        if ((r5 instanceof zzah) == false) goto L21;
        if (Double.isNaN(r5.zzd().doubleValue()) == false) goto L14;
    L19:
        return false;
    L14:
        if (Double.isNaN(r6.zzd().doubleValue()) == true) goto L19;
        if (r5.zzd().doubleValue() != r6.zzd().doubleValue()) goto L19;
        return true;
    L21:
        if ((r5 instanceof zzas) == false) goto L25;
        return r5.zzc().equals(r6.zzc());
    L25:
        if ((r5 instanceof zzaf) == true) goto L27;
        if (r5 != r6) goto L30;
        return true;
    L30:
        return false;
    L27:
        return r5.zze().equals(r6.zze());
    L33:
        if ((r5 instanceof zzat) == true) goto L37;
        if ((r5 instanceof zzam) == true) goto L37;
    L41:
        boolean r0 = r5 instanceof zzah;
        if (r0 == true) goto L44;
    L47:
        boolean r2 = r5 instanceof zzas;
        if (r2 == false) goto L54;
        if ((r6 instanceof zzah) == false) goto L54;
        return zzd(new zzah(r5.zzd()), r6);
    L54:
        if ((r5 instanceof zzaf) == false) goto L58;
        return zzd(new zzah(r5.zzd()), r6);
    L58:
        if ((r6 instanceof zzaf) == true) goto L60;
        if (r2 == true) goto L64;
        if (r0 == true) goto L64;
    L68:
        if ((r5 instanceof zzak) == true) goto L70;
    L75:
        return false;
    L70:
        if ((r6 instanceof zzas) == true) goto L74;
        if ((r6 instanceof zzah) == false) goto L75;
    L74:
        return zzd(new zzas(r5.zzc()), r6);
    L64:
        if ((r6 instanceof zzak) == false) goto L68;
        return zzd(r5, new zzas(r6.zzc()));
    L60:
        return zzd(r5, new zzah(r6.zzd()));
    L44:
        if ((r6 instanceof zzas) == false) goto L47;
        return zzd(r5, new zzah(r6.zzd()));
    L37:
        if ((r6 instanceof zzat) == false) goto L39;
    L76:
        return true;
    L39:
        if ((r6 instanceof zzam) == false) goto L41;
        goto L41
    }

    private static boolean zze(zzao r4, zzao r5) {
        if ((r4 instanceof zzak) == false) goto L6;
        r4 = new zzas(r4.zzc());
    L6:
        if ((r5 instanceof zzak) == false) goto L9;
        r5 = new zzas(r5.zzc());
    L9:
        if ((r4 instanceof zzas) == false) goto L13;
        if ((r5 instanceof zzas) == false) goto L13;
    L18:
        if (zzc(r5, r4) == true) goto L21;
        return true;
    L21:
        return false;
    L13:
        if (Double.isNaN(r4.zzd().doubleValue()) == true) goto L21;
        if (Double.isNaN(r5.zzd().doubleValue()) == false) goto L18;
        goto L21
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String r4, zzg r5, List r6) {
        zzh.zza(zzh.zze(r4).name(), 2, r6);
        zzao r0 = r5.zza((zzao) r6.get(0));
        zzao r52 = r5.zza((zzao) r6.get(1));
        int r62 = zzh.zze(r4).ordinal();
        if (r62 != 23) goto L5;
        boolean r3 = zzd(r0, r52);
    L22:
        if (r3 == false) goto L26;
        return zzao.zzk;
    L26:
        return zzao.zzl;
    L5:
        if (r62 != 48) goto L7;
        boolean r32 = zzd(r0, r52);
    L14:
        r3 = !r32;
        goto L22
    L7:
        if (r62 != 42) goto L9;
        r3 = zzc(r0, r52);
        goto L22
    L9:
        if (r62 == 43) goto L18;
        switch(r62) {
            case 37: goto L17;
            case 38: goto L16;
            case 39: goto L15;
            case 40: goto L13;
            default: goto L12;
        };
    L13:
        r32 = zzh.zzf(r0, r52);
        goto L14
    L15:
        r3 = zzh.zzf(r0, r52);
        goto L22
    L16:
        r3 = zze(r52, r0);
        goto L22
    L17:
        r3 = zzc(r52, r0);
        goto L22
    L12:
        return super.zzb(r4);
    L18:
        r3 = zze(r0, r52);
        goto L22
    }
}
