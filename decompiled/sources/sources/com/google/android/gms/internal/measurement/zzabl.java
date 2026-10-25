package com.google.android.gms.internal.measurement;

/* loaded from: classes4.dex */
public final class zzabl extends zzabp {
    private static final zzabp zza = null;

    static {
        zza = new zzabl();
    }

    private zzabl() {
    }

    public static zzabp zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzabp
    public final int zzb(zzabm r6, int r7, String r8, int r9, int r10, int r11) {
        char r5 = r8.charAt(r11);
        if ((r5 & ' ') != 0) goto L5;
        boolean r0 = true;
    L6:
        zzza r102 = zzza.zzb(r8, r10, r11, r0);
        zzyz r02 = zzyz.zza(r5);
        int r2 = r11 + 1;
        if (r02 == null) goto L14;
        if (r102.zzi(r02) == false) goto L12;
        zzabh r52 = zzabj.zza(r7, r02, r102);
    L37:
        r6.zzk(r9, r2, r52);
        return r2;
    L12:
        throw zzabo.zza("invalid format specifier", r8, r9, r2);
    L14:
        if (r5 == 't') goto L31;
        if (r5 == 'T') goto L31;
        if (r5 == 'h') goto L26;
        if (r5 == 'H') goto L26;
        throw zzabo.zza("invalid format specification", r8, r9, r2);
    L26:
        if (r102.zzh(160, false) == false) goto L29;
        r52 = new zzabk(r102, r7, r102);
        goto L37
    L29:
        throw zzabo.zza("invalid format specification", r8, r9, r2);
    L31:
        if (r102.zzh(160, false) == false) goto L44;
        int r112 = r11 + 2;
        if (r112 > r8.length()) goto L42;
        zzabf r53 = zzabf.zza(r8.charAt(r2));
        if (r53 == null) goto L40;
        r52 = zzabg.zza(r53, r102, r7);
        r2 = r112;
        goto L37
    L40:
        throw zzabo.zzb("illegal date/time conversion", r8, r2);
    L42:
        throw zzabo.zzb("truncated format specifier", r8, r9);
    L44:
        throw zzabo.zza("invalid format specification", r8, r9, r2);
    L5:
        r0 = false;
        goto L6
    }
}
