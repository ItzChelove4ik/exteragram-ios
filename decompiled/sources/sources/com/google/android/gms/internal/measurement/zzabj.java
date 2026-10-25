package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

/* loaded from: classes4.dex */
public final class zzabj extends zzabh {
    private static final Map zza = null;
    private final zzyz zzb;

    static {
        EnumMap r0 = new EnumMap(zzyz.class);
        zzyz[] r1 = zzyz.values();
        int r2 = r1.length;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L8;
        zzyz r5 = r1[r4];
        zzabj[] r7 = new zzabj[10];
        int r8 = 0;
    L5:
        if (r8 >= 10) goto L7;
        r7[r8] = new zzabj(r8, r5, zzza.zza());
        r8 = r8 + 1;
        goto L5
    L7:
        r0.put(r5, r7);
        r4 = r4 + 1;
        goto L3
    L8:
        zza = Collections.unmodifiableMap(r0);
    }

    private zzabj(int r1, zzyz r2, zzza r3) {
        super(r3, r1);
        zzabr.zza(r2, "format char");
        this.zzb = r2;
        if (r3.zze() == true) goto L9;
        int r0 = r2.zzb();
        if (r3.zzk() == false) goto L7;
        r0 = r0 & 65503;
    L7:
        StringBuilder r12 = new StringBuilder("%");
        r3.zzl(r12);
        r12.append((char) r0);
        return;
    L9:
        r2.zze();
    }

    public static zzabj zza(int r1, zzyz r2, zzza r3) {
        if (r1 >= 10) goto L9;
        if (r3.zze() == false) goto L9;
        zzabj[] r22 = (zzabj[]) zza.get(r2);
        zzabr.zza(r22, "default parameter");
        return r22[r1];
    L9:
        return new zzabj(r1, r2, r3);
    }

    @Override // com.google.android.gms.internal.measurement.zzabh
    public final void zzb(zzabi r2, Object r3) {
        r2.zzc(r3, this.zzb, zzd());
    }
}
