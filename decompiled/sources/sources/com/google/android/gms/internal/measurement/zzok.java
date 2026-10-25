package com.google.android.gms.internal.measurement;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* loaded from: classes4.dex */
final class zzok implements Comparable {
    private static final AtomicReferenceFieldUpdater zzc = null;
    private final String zza;
    private volatile Object zzb;

    static {
        zzc = AtomicReferenceFieldUpdater.newUpdater(zzok.class, Object.class, "zzb");
    }

    public /* synthetic */ zzok(String r1, byte[] r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object r1) {
        return this.zza.compareTo((String) r1);
    }

    public final /* synthetic */ String zza() {
        return this.zza;
    }

    public final /* synthetic */ void zzb(byte[] r6, boolean r7) {
        int r0 = 0;
    L3:
        Object r1 = this.zzb;
        if ((r1 instanceof byte[]) == false) goto L9;
        byte[] r02 = (byte[]) r1;
        if (Arrays.equals(r6, r02) == true) goto L19;
        r0 = 1;
        byte[][] r2 = {r02, r6};
    L17:
        if (AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(zzc, this, r1, r2) == false) goto L3;
        return;
    L19:
        return;
    L9:
        byte[][] r22 = (byte[][]) r1;
    L10:
        int r3 = r22.length;
        if (r0 >= r3) goto L15;
        if (Arrays.equals(r6, r22[r0]) == true) goto L25;
        r0 = r0 + 1;
        goto L10
    L25:
        return;
    L15:
        r2 = (byte[][]) Arrays.copyOf(r22, r3 + 1);
        r2[r3] = r6;
        goto L17
    }
}
