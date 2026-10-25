package com.google.android.gms.internal.measurement;

import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes4.dex */
public final class zzol implements zzoh {
    private final AtomicBoolean zza;
    private final ConcurrentMap zzb;
    private final ConcurrentMap zzc;

    public zzol() {
        this.zza = new AtomicBoolean(false);
        new ConcurrentHashMap();
        this.zzb = new ConcurrentHashMap();
        new ConcurrentHashMap();
        this.zzc = new ConcurrentHashMap();
    }

    @Override // com.google.android.gms.internal.measurement.zzoh
    public final void zza(zzacr r10, Set r11, String r12) {
        if (r11.isEmpty() == false) goto L5;
    L7:
        byte[] r102 = r10.zzm();
        this.zzb.compute(r12, new zzoi(r102));
        Iterator r112 = r11.iterator();
    L9:
        if (r112.hasNext() == false) goto L32;
        AtomicReference r0 = (AtomicReference) this.zzc.putIfAbsent((String) r112.next(), new AtomicReference(new zzok(r12, r102, null)));
        if (r0 == null) goto L9;
    L12:
        Object r3 = r0.get();
        if ((r3 instanceof zzok) == false) goto L21;
        zzok r4 = (zzok) r3;
        if (r12.equals(r4.zza()) == true) goto L16;
        zzok r6 = new zzok(r12, r102, null);
        if (r12.compareTo(r4.zza()) >= 0) goto L20;
        zzok[] r7 = {r6, r4};
    L30:
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(r0, r3, r7) == false) goto L12;
    L20:
        r7 = new zzok[]{r4, r6};
        goto L30
    L16:
        r4.zzb(r102, false);
        goto L9
    L21:
        zzok[] r42 = (zzok[]) r3;
        int r62 = Arrays.binarySearch(r42, r12);
        if (r62 >= 0) goto L23;
        int r63 = ~r62;
        int r72 = r42.length;
        int r8 = r72 + 1;
        int r73 = r72 - r63;
        if (r73 != 0) goto L27;
        r7 = (zzok[]) Arrays.copyOf(r42, r8);
    L28:
        r7[r63] = new zzok(r12, r102, null);
        goto L30
    L27:
        zzok[] r82 = new zzok[r8];
        System.arraycopy(r42, 0, r82, 0, r63);
        System.arraycopy(r42, r63, r82, r63 + 1, r73);
        r7 = r82;
        goto L28
    L23:
        r42[r62].zzb(r102, false);
        goto L9
    L32:
        return;
    L5:
        if (this.zza.getAndSet(true) == true) goto L7;
        zzbu.zza().zzb(new zzoj(this, null));
        goto L7
    }
}
