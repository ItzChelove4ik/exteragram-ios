package com.google.android.gms.internal.clearcut;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
final class zzda extends zzcy {
    private static final Class<?> zzlv = null;

    static {
        zzlv = Collections.unmodifiableList(Collections.EMPTY_LIST).getClass();
    }

    private zzda() {
        super(null);
    }

    public /* synthetic */ zzda(zzcz r1) {
        this();
    }

    private static <E> List<E> zzb(Object r0, long r1) {
        return (List) zzfd.zzo(r0, r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcy
    public final void zza(Object r3, long r4) {
        List r2 = (List) zzfd.zzo(r3, r4);
        if ((r2 instanceof zzcx) == false) goto L6;
        Object r22 = ((zzcx) r2).zzbu();
    L9:
        zzfd.zza(r3, r4, r22);
        return;
    L6:
        if (zzlv.isAssignableFrom(r2.getClass()) == false) goto L8;
        return;
    L8:
        r22 = Collections.unmodifiableList(r2);
        goto L9
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.clearcut.zzcy
    public final <E> void zza(Object r4, Object r5, long r6) {
        List r3 = zzb(r5, r6);
        int r52 = r3.size();
        List r0 = zzb(r4, r6);
        if (r0.isEmpty() == false) goto L10;
        if ((r0 instanceof zzcx) == false) goto L7;
        r0 = new zzcw(r52);
    L8:
        zzfd.zza(r4, r6, r0);
    L16:
        int r53 = r0.size();
        int r1 = r3.size();
        if (r53 <= 0) goto L20;
        if (r1 <= 0) goto L20;
        r0.addAll(r3);
    L20:
        if (r53 <= 0) goto L22;
        r3 = r0;
    L22:
        zzfd.zza(r4, r6, r3);
        return;
    L7:
        r0 = new ArrayList(r52);
        goto L8
    L10:
        if (zzlv.isAssignableFrom(r0.getClass()) == false) goto L14;
        ArrayList r12 = new ArrayList(r0.size() + r52);
        r12.addAll(r0);
        zzcw r13 = r12;
    L12:
        zzfd.zza(r4, r6, r13);
        r0 = r13;
        goto L16
    L14:
        if ((r0 instanceof zzfa) == false) goto L16;
        zzcw r14 = new zzcw(r0.size() + r52);
        r14.addAll((zzfa) r0);
        r13 = r14;
        goto L12
    }
}
