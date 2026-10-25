package com.google.android.gms.internal.vision;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes5.dex */
final class zzjw extends zzju {
    private static final Class<?> zza = null;

    static {
        zza = Collections.unmodifiableList(Collections.EMPTY_LIST).getClass();
    }

    private zzjw() {
        super(null);
    }

    public /* synthetic */ zzjw(zzjx r1) {
        this();
    }

    private static <L> List<L> zza(Object r3, long r4, int r6) {
        List<L> r0 = zzc(r3, r4);
        if (r0.isEmpty() == false) goto L16;
        if ((r0 instanceof zzjv) == false) goto L8;
        List<L> r02 = new zzjs(r6);
    L13:
        zzma.zza(r3, r4, r02);
        return r02;
    L8:
        if ((r0 instanceof zzkw) == true) goto L10;
    L12:
        r02 = new ArrayList(r6);
        goto L13
    L10:
        if ((r0 instanceof zzjl) == false) goto L12;
        r02 = ((zzjl) r0).zza(r6);
        goto L13
    L16:
        if (zza.isAssignableFrom(r0.getClass()) == false) goto L20;
        ArrayList r1 = new ArrayList(r0.size() + r6);
        r1.addAll(r0);
        zzma.zza(r3, r4, r1);
        return r1;
    L20:
        if ((r0 instanceof zzlz) == false) goto L24;
        zzjs r12 = new zzjs(r0.size() + r6);
        r12.addAll((zzlz) r0);
        zzma.zza(r3, r4, r12);
        return r12;
    L24:
        if ((r0 instanceof zzkw) == true) goto L26;
    L31:
        return r0;
    L26:
        if ((r0 instanceof zzjl) == false) goto L31;
        zzjl r13 = (zzjl) r0;
        if (r13.zza() == true) goto L31;
        zzjl r62 = r13.zza(r0.size() + r6);
        zzma.zza(r3, r4, r62);
        return r62;
    }

    private static <E> List<E> zzc(Object r0, long r1) {
        return (List) zzma.zzf(r0, r1);
    }

    @Override // com.google.android.gms.internal.vision.zzju
    public final <E> void zza(Object r3, Object r4, long r5) {
        List r2 = zzc(r4, r5);
        List r42 = zza(r3, r5, r2.size());
        int r0 = r42.size();
        int r1 = r2.size();
        if (r0 <= 0) goto L6;
        if (r1 <= 0) goto L6;
        r42.addAll(r2);
    L6:
        if (r0 <= 0) goto L8;
        r2 = r42;
    L8:
        zzma.zza(r3, r5, r2);
    }

    @Override // com.google.android.gms.internal.vision.zzju
    public final void zzb(Object r3, long r4) {
        List r2 = (List) zzma.zzf(r3, r4);
        if ((r2 instanceof zzjv) == false) goto L6;
        Object r22 = ((zzjv) r2).zze();
    L17:
        zzma.zza(r3, r4, r22);
        return;
    L6:
        if (zza.isAssignableFrom(r2.getClass()) == false) goto L9;
        return;
    L9:
        if ((r2 instanceof zzkw) == true) goto L11;
    L16:
        r22 = Collections.unmodifiableList(r2);
        goto L17
    L11:
        if ((r2 instanceof zzjl) == false) goto L16;
        zzjl r23 = (zzjl) r2;
        if (r23.zza() == false) goto L19;
        r23.zzb();
        return;
    }
}
