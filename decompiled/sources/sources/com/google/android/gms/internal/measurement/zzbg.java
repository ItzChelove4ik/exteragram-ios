package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes4.dex */
public final class zzbg extends zzav {
    public zzbg() {
        this.zza.add(zzbk.zzA);
        this.zza.add(zzbk.zzB);
        this.zza.add(zzbk.zzC);
        this.zza.add(zzbk.zzD);
        this.zza.add(zzbk.zzE);
        this.zza.add(zzbk.zzF);
        this.zza.add(zzbk.zzG);
        this.zza.add(zzbk.zzan);
    }

    private static zzao zzc(zzbe r0, zzao r1, zzao r2) {
        return zze(r0, r1.zzf(), r2);
    }

    private static zzao zzd(zzbe r1, zzao r2, zzao r3) {
        if ((r2 instanceof Iterable) == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Non-iterable type in for...of loop.");
        return null;
    L5:
        return zze(r1, ((Iterable) r2).iterator(), r3);
    }

    private static zzao zze(zzbe r3, Iterator r4, zzao r5) {
        if (r4 == null) goto L15;
    L4:
        if (r4.hasNext() == false) goto L15;
        zzao r0 = r3.zza((zzao) r4.next()).zzb((zzae) r5);
        if ((r0 instanceof zzag) == false) goto L4;
        zzag r02 = (zzag) r0;
        if ("break".equals(r02.zzg()) == true) goto L10;
        if ("return".equals(r02.zzg()) == false) goto L4;
        return r02;
    L10:
        return zzao.zzf;
    L15:
        return zzao.zzf;
    }

    @Override // com.google.android.gms.internal.measurement.zzav
    public final zzao zza(String r10, zzg r11, List r12) {
        zzbk r0 = zzbk.zza;
        int r02 = zzh.zze(r10).ordinal();
        if (r02 != 65) goto L5;
        zzh.zza(zzbk.zzan.name(), 4, r12);
        zzao r9 = (zzao) r12.get(0);
        zzao r102 = (zzao) r12.get(1);
        zzao r03 = (zzao) r12.get(2);
        zzao r122 = r11.zza((zzao) r12.get(3));
        if (r11.zza(r03).zze().booleanValue() == false) goto L83;
        zzao r04 = r11.zzb((zzae) r122);
        if ((r04 instanceof zzag) == false) goto L83;
        zzag r05 = (zzag) r04;
        if ("break".equals(r05.zzg()) == false) goto L80;
        return zzao.zzf;
    L80:
        if ("return".equals(r05.zzg()) == false) goto L83;
        return r05;
    L83:
        if (r11.zza(r9).zze().booleanValue() == false) goto L95;
        zzao r06 = r11.zzb((zzae) r122);
        if ((r06 instanceof zzag) == false) goto L93;
        zzag r07 = (zzag) r06;
        if ("break".equals(r07.zzg()) == true) goto L89;
        if ("return".equals(r07.zzg()) == false) goto L93;
        return r07;
    L89:
        return zzao.zzf;
    L93:
        r11.zza(r102);
        goto L83
    L95:
        return zzao.zzf;
    L5:
        switch(r02) {
            case 26: goto L64;
            case 27: goto L58;
            case 28: goto L52;
            case 29: goto L26;
            case 30: goto L20;
            case 31: goto L14;
            case 32: goto L8;
            default: goto L7;
        };
    L8:
        zzh.zza(zzbk.zzG.name(), 3, r12);
        if ((r12.get(0) instanceof zzas) == false) goto L12;
        String r92 = ((zzao) r12.get(0)).zzc();
        return zzd(new zzbd(r11, r92), r11.zza((zzao) r12.get(1)), r11.zza((zzao) r12.get(2)));
    L12:
        f$$ExternalSyntheticBUOutline1.m("Variable name in FOR_OF_LET must be a string");
        return null;
    L14:
        zzh.zza(zzbk.zzF.name(), 3, r12);
        if ((r12.get(0) instanceof zzas) == false) goto L18;
        String r93 = ((zzao) r12.get(0)).zzc();
        return zzd(new zzbc(r11, r93), r11.zza((zzao) r12.get(1)), r11.zza((zzao) r12.get(2)));
    L18:
        f$$ExternalSyntheticBUOutline1.m("Variable name in FOR_OF_CONST must be a string");
        return null;
    L20:
        zzh.zza(zzbk.zzE.name(), 3, r12);
        if ((r12.get(0) instanceof zzas) == false) goto L24;
        String r94 = ((zzao) r12.get(0)).zzc();
        return zzd(new zzbf(r11, r94), r11.zza((zzao) r12.get(1)), r11.zza((zzao) r12.get(2)));
    L24:
        f$$ExternalSyntheticBUOutline1.m("Variable name in FOR_OF must be a string");
        return null;
    L26:
        zzh.zza(zzbk.zzD.name(), 4, r12);
        zzao r95 = r11.zza((zzao) r12.get(0));
        if ((r95 instanceof zzae) == false) goto L50;
        zzae r96 = (zzae) r95;
        zzao r103 = (zzao) r12.get(1);
        zzao r08 = (zzao) r12.get(2);
        zzao r123 = r11.zza((zzao) r12.get(3));
        zzg r1 = r11.zzc();
        int r2 = 0;
    L30:
        if (r2 >= r96.zzh()) goto L33;
        String r5 = r96.zzl(r2).zzc();
        r1.zze(r5, r11.zzh(r5));
        r2 = r2 + 1;
    L33:
        if (r11.zza(r103).zze().booleanValue() == false) goto L49;
        zzao r22 = r11.zzb((zzae) r123);
        if ((r22 instanceof zzag) == false) goto L43;
        zzag r23 = (zzag) r22;
        if ("break".equals(r23.zzg()) == true) goto L39;
        if ("return".equals(r23.zzg()) == false) goto L43;
        return r23;
    L39:
        return zzao.zzf;
    L43:
        zzg r24 = r11.zzc();
        int r52 = 0;
    L45:
        if (r52 >= r96.zzh()) goto L47;
        String r6 = r96.zzl(r52).zzc();
        r24.zze(r6, r1.zzh(r6));
        r52 = r52 + 1;
        goto L45
    L47:
        r24.zza(r08);
        r1 = r24;
        goto L33
    L49:
        return zzao.zzf;
    L50:
        f$$ExternalSyntheticBUOutline1.m("Initializer variables in FOR_LET must be an ArrayList");
        return null;
    L52:
        zzh.zza(zzbk.zzC.name(), 3, r12);
        if ((r12.get(0) instanceof zzas) == false) goto L56;
        String r97 = ((zzao) r12.get(0)).zzc();
        return zzc(new zzbd(r11, r97), r11.zza((zzao) r12.get(1)), r11.zza((zzao) r12.get(2)));
    L56:
        f$$ExternalSyntheticBUOutline1.m("Variable name in FOR_IN_LET must be a string");
        return null;
    L58:
        zzh.zza(zzbk.zzB.name(), 3, r12);
        if ((r12.get(0) instanceof zzas) == false) goto L62;
        String r98 = ((zzao) r12.get(0)).zzc();
        return zzc(new zzbc(r11, r98), r11.zza((zzao) r12.get(1)), r11.zza((zzao) r12.get(2)));
    L62:
        f$$ExternalSyntheticBUOutline1.m("Variable name in FOR_IN_CONST must be a string");
        return null;
    L64:
        zzh.zza(zzbk.zzA.name(), 3, r12);
        if ((r12.get(0) instanceof zzas) == false) goto L68;
        String r99 = ((zzao) r12.get(0)).zzc();
        return zzc(new zzbf(r11, r99), r11.zza((zzao) r12.get(1)), r11.zza((zzao) r12.get(2)));
    L68:
        f$$ExternalSyntheticBUOutline1.m("Variable name in FOR_IN must be a string");
        return null;
    L7:
        return super.zzb(r10);
    }
}
