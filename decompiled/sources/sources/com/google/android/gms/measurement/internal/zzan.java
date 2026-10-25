package com.google.android.gms.measurement.internal;

import java.util.EnumMap;

/* loaded from: classes5.dex */
final class zzan {
    private final EnumMap zza;

    public zzan() {
        this.zza = new EnumMap(zzjk.class);
    }

    private zzan(EnumMap r3) {
        EnumMap r0 = new EnumMap(zzjk.class);
        this.zza = r0;
        r0.putAll(r3);
    }

    public static zzan zzd(String r7) {
        EnumMap r0 = new EnumMap(zzjk.class);
        if (r7.length() < zzjk.values().length) goto L13;
        int r1 = 0;
        if (r7.charAt(0) != '1') goto L13;
        zzjk[] r2 = zzjk.values();
        int r3 = r2.length;
        int r4 = 1;
    L8:
        if (r1 >= r3) goto L11;
        int r6 = r4 + 1;
        r0.put(r2[r1], zzam.zza(r7.charAt(r4)));
        r1 = r1 + 1;
        r4 = r6;
        goto L8
    L11:
        return new zzan(r0);
    L13:
        return new zzan();
    }

    public final String toString() {
        StringBuilder r0 = new StringBuilder("1");
        zzjk[] r1 = zzjk.values();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L9;
        zzjk r4 = r1[r3];
        zzam r42 = (zzam) this.zza.get(r4);
        if (r42 != null) goto L7;
        r42 = zzam.zza;
    L7:
        r0.append(r42.zzb());
        r3 = r3 + 1;
        goto L3
    L9:
        return r0.toString();
    }

    public final zzam zza(zzjk r1) {
        zzam r0 = (zzam) this.zza.get(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        return zzam.zza;
    }

    public final void zzb(zzjk r3, int r4) {
        zzam r0 = zzam.zza;
        if (r4 != (-30)) goto L5;
        r0 = zzam.zzh;
    L16:
        this.zza.put(r3, r0);
        return;
    L5:
        if (r4 != (-20)) goto L7;
    L14:
        r0 = zzam.zzf;
        goto L16
    L7:
        if (r4 == (-10)) goto L13;
        if (r4 == 0) goto L14;
        if (r4 != 30) goto L16;
        r0 = zzam.zze;
        goto L16
    L13:
        r0 = zzam.zzd;
        goto L16
    }

    public final void zzc(zzjk r1, zzam r2) {
        this.zza.put(r1, r2);
    }
}
