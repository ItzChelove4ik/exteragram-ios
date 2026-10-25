package com.google.android.gms.internal.measurement;

import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ListenableFuture;

/* loaded from: classes4.dex */
final class zzvk extends AbstractFuture {
    private zzvm zza;
    private final int zzb;

    public /* synthetic */ zzvk(zzvm r1, int r2, byte[] r3) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final void afterDone() {
        zzvm r0 = this.zza;
        this.zza = null;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.zze() == false) goto L13;
    L7:
        zzvl r2 = (zzvl) r0.zzg().get();
        if (r2 == null) goto L19;
        int r3 = this.zzb;
        if (r2.zza() > r3) goto L20;
        r2.cancel(true);
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(r0.zzg(), r2, null) == false) goto L7;
        return;
    L20:
        return;
    L19:
        return;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        zzvm r0 = this.zza;
        if (r0 != null) goto L5;
        return null;
    L5:
        AsyncCallable r02 = r0.zzf().zza();
        if (r02 != null) goto L8;
        return null;
    L8:
        String r03 = r02.toString();
        StringBuilder r2 = new StringBuilder(r03.length() + 11);
        r2.append("callable=[");
        r2.append(r03);
        r2.append("]");
        String r1 = r2.toString();
        zzvl r4 = (zzvl) this.zza.zzg().get();
        if (r4 == null) goto L12;
        int r22 = r1.length();
        String r42 = r4.toString();
        StringBuilder r3 = new StringBuilder(((r22 + 9) + r42.length()) + 1);
        r3.append(r1);
        r3.append(", trial=[");
        r3.append(r42);
        r3.append("]");
        return r3.toString();
    L12:
        return r1;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    public final boolean setFuture(ListenableFuture r1) {
        return super.setFuture(r1);
    }
}
