package com.google.android.gms.internal.clearcut;

/* loaded from: classes4.dex */
final class zzdb extends zzcy {
    private zzdb() {
        super(null);
    }

    public /* synthetic */ zzdb(zzcz r1) {
        this();
    }

    private static <E> zzcn<E> zzc(Object r0, long r1) {
        return (zzcn) zzfd.zzo(r0, r1);
    }

    @Override // com.google.android.gms.internal.clearcut.zzcy
    public final void zza(Object r1, long r2) {
        zzc(r1, r2).zzv();
    }

    @Override // com.google.android.gms.internal.clearcut.zzcy
    public final <E> void zza(Object r4, Object r5, long r6) {
        zzcn r3 = zzc(r4, r6);
        zzcn r52 = zzc(r5, r6);
        int r0 = r3.size();
        int r1 = r52.size();
        if (r0 <= 0) goto L9;
        if (r1 <= 0) goto L9;
        if (r3.zzu() == true) goto L8;
        r3 = r3.zzi(r1 + r0);
    L8:
        r3.addAll(r52);
    L9:
        if (r0 <= 0) goto L11;
        r52 = r3;
    L11:
        zzfd.zza(r4, r6, r52);
    }
}
