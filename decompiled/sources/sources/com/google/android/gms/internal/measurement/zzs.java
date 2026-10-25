package com.google.android.gms.internal.measurement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzs extends zzai {
    final boolean zza;
    final boolean zzb;
    final /* synthetic */ zzt zzc;

    public zzs(zzt r1, boolean r2, boolean r3) {
        Objects.requireNonNull(r1);
        this.zzc = r1;
        super("log");
        this.zza = r2;
        this.zzb = r3;
    }

    @Override // com.google.android.gms.internal.measurement.zzai
    public final zzao zza(zzg r12, List r13) {
        zzh.zzb("log", 1, r13);
        if (r13.size() != 1) goto L6;
        zzt r0 = this.zzc;
        String r3 = r12.zza((zzao) r13.get(0)).zzc();
        boolean r5 = this.zza;
        boolean r6 = this.zzb;
        r0.zzb().zza(3, r3, Collections.EMPTY_LIST, r5, r6);
        return zzao.zzf;
    L6:
        int r02 = zzh.zzg(r12.zza((zzao) r13.get(0)).zzd().doubleValue());
        int r32 = 2;
        if (r02 == 2) goto L17;
        int r4 = 3;
        if (r02 == 3) goto L16;
        if (r02 != 5) goto L12;
        int r62 = 5;
    L18:
        String r7 = r12.zza((zzao) r13.get(1)).zzc();
        if (r13.size() != 2) goto L22;
        zzt r122 = this.zzc;
        boolean r9 = this.zza;
        boolean r10 = this.zzb;
        r122.zzb().zza(r62, r7, Collections.EMPTY_LIST, r9, r10);
        return zzao.zzf;
    L22:
        ArrayList r8 = new ArrayList();
    L24:
        if (r32 >= Math.min(r13.size(), 5)) goto L26;
        r8.add(r12.zza((zzao) r13.get(r32)).zzc());
        r32 = r32 + 1;
        goto L24
    L26:
        zzt r123 = this.zzc;
        r123.zzb().zza(r62, r7, r8, this.zza, this.zzb);
        return zzao.zzf;
    L12:
        if (r02 == 6) goto L14;
    L13:
        r62 = r4;
        goto L18
    L14:
        r62 = 2;
        goto L18
    L16:
        r62 = 1;
        goto L18
    L17:
        r4 = 4;
        goto L13
    }
}
