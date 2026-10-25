package com.google.android.gms.internal.measurement;

import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public final class zzz {
    final TreeMap zza;
    final TreeMap zzb;

    public zzz() {
        this.zza = new TreeMap();
        this.zzb = new TreeMap();
    }

    private static final int zzc(zzg r0, zzan r1, zzao r2) {
        zzao r02 = r1.zza(r0, Collections.singletonList(r2));
        if ((r02 instanceof zzah) == true) goto L5;
        return -1;
    L5:
        return zzh.zzg(r02.zzd().doubleValue());
    }

    public final void zza(String r1, int r2, zzan r3, String r4) {
        if ("create".equals(r4) == false) goto L6;
        TreeMap r0 = this.zzb;
    L9:
        if (r0.containsKey(Integer.valueOf(r2)) == false) goto L11;
        r2 = ((Integer) r0.lastKey()).intValue() + 1;
    L11:
        r0.put(Integer.valueOf(r2), r3);
        return;
    L6:
        if ("edit".equals(r4) == false) goto L13;
        r0 = this.zza;
        goto L9
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("Unknown callback type: ".concat(String.valueOf(r4)));
    }

    public final void zzb(zzg r7, zzab r8) {
        zzl r0 = new zzl(r8);
        TreeMap r1 = this.zza;
        Iterator r2 = r1.keySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L10;
        Integer r3 = (Integer) r2.next();
        zzaa r4 = r8.zzc().zzg();
        int r32 = zzc(r7, (zzan) r1.get(r3), r0);
        if (r32 == 2) goto L9;
        if (r32 != (-1)) goto L4;
    L9:
        r8.zzd(r4);
        goto L4
    L10:
        TreeMap r6 = this.zzb;
        Iterator r82 = r6.keySet().iterator();
    L12:
        if (r82.hasNext() == false) goto L14;
        zzc(r7, (zzan) r6.get((Integer) r82.next()), r0);
        goto L12
    }
}
