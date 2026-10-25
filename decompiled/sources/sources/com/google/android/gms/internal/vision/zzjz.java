package com.google.android.gms.internal.vision;

/* loaded from: classes5.dex */
final class zzjz extends zzju {
    private zzjz() {
        super(null);
    }

    public /* synthetic */ zzjz(zzjx r1) {
        this();
    }

    private static <E> zzjl<E> zzc(Object r0, long r1) {
        return (zzjl) zzma.zzf(r0, r1);
    }

    @Override // com.google.android.gms.internal.vision.zzju
    public final <E> void zza(Object r4, Object r5, long r6) {
        zzjl r3 = zzc(r4, r6);
        zzjl r52 = zzc(r5, r6);
        int r0 = r3.size();
        int r1 = r52.size();
        if (r0 <= 0) goto L9;
        if (r1 <= 0) goto L9;
        if (r3.zza() == true) goto L8;
        r3 = r3.zza(r1 + r0);
    L8:
        r3.addAll(r52);
    L9:
        if (r0 <= 0) goto L11;
        r52 = r3;
    L11:
        zzma.zza(r4, r6, r52);
    }

    @Override // com.google.android.gms.internal.vision.zzju
    public final void zzb(Object r1, long r2) {
        zzc(r1, r2).zzb();
    }
}
