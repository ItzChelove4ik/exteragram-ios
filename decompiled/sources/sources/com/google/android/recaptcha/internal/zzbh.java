package com.google.android.recaptcha.internal;

import android.content.Context;
import android.content.Intent;
import androidx.media3.exoplayer.mediacodec.AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.BuildersKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzbh implements zzbe {
    private final zzbr zza;
    private final Context zzb;
    private final AtomicReference zzc;
    private final zzhu zzd;

    public zzbh(zzbr r1, zzhu r2, Context r3) {
        this.zza = r1;
        this.zzd = r2;
        this.zzb = r3;
        this.zzc = new AtomicReference(null);
    }

    private final void zzb(zzbk r9) {
        AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(this.zzc, r9, null);
        zzbj.zza();
        BuildersKt.launch$default(this.zzd.zza(), null, null, new zzbg(r9, null), 3, null);
    }

    @Override // com.google.android.recaptcha.internal.zzbe
    public final Object zza(zzalc r8, Continuation r9) {
        if ((r9 instanceof zzbf) == false) goto L7;
        zzbf r0 = (zzbf) r9;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r92 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L18;
        if (r2 != 1) goto L16;
        zzbp r82 = r0.zzd;
        ResultKt.throwOnFailure(r92);     // Catch: Throwable -> L14
    L23:
        zzb(r82);
        return r92;
    L14:
        th = move-exception;
        Throwable r93 = th;
    L28:
        zzb(r82);
        throw r93;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r92);
        zzbp r5 = new zzbp(r8, this.zza, this.zzd.zza());
        if (AsynchronousMediaCodecBufferEnqueuer$$ExternalSyntheticBackportWithForwarding0.m(this.zzc, null, r5) == false) goto L31;
        r0.zzd = r5;     // Catch: Throwable -> L26
        r0.zzc = 1;     // Catch: Throwable -> L26
        zzbj.zzc(r5);     // Catch: Throwable -> L26
        Context r94 = this.zzb;     // Catch: Throwable -> L26
        Intent r83 = new Intent(r94, zzca.class);     // Catch: Throwable -> L26
        r83.setFlags(268435456);     // Catch: Throwable -> L26
        r94.startActivity(r83);     // Catch: Throwable -> L26
        r92 = r5.zza(r0);     // Catch: Throwable -> L26
        if (r92 == r12) goto L25;
        r82 = r5;
        goto L23
    L25:
        return r12;
    L26:
        th = move-exception;
        r93 = th;
        r82 = r5;
        goto L28
    L31:
        throw new zzhg(zzhf.zzb, zzhe.zzD, null, null, 12, null);
    L7:
        r0 = new zzbf(this, r9);
        goto L8
    }
}
