package com.google.android.recaptcha.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.TimeoutCancellationException;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.chromium.net.CronetEngine;
import org.chromium.net.UrlRequest;
import org.chromium.net.apihelpers.UploadDataProviders;

/* loaded from: classes5.dex */
public final class zzna implements zzmx {
    private final zznd zza;
    private final CronetEngine zzb;
    private final ExecutorService zzc;

    public zzna(zznd r1, CronetEngine r2, ExecutorService r3) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
    }

    public static final /* synthetic */ Object zzd(zzna r0, String r1, String r2, byte[] r3, Map r4, Continuation r5) {
        return r0.zze(null, null, null, null, r5);
    }

    private final Object zze(String r8, String r9, byte[] r10, Map r11, Continuation r12) {
        if ((r12 instanceof zzmy) == false) goto L7;
        zzmy r0 = (zzmy) r12;
        int r1 = r0.zzd;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzd = r1 - Integer.MIN_VALUE;
    L8:
        Object r122 = r0.zzb;
        Object r13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzd;
        if (r2 == 0) goto L16;
        if (r2 != 1) goto L14;
        Map r7 = (Map) r0.zza;
        ResultKt.throwOnFailure(r122);     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        return r122;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L16:
        ResultKt.throwOnFailure(r122);
        if (this.zza.zza(r8) == false) goto L40;
        r0.zze = r8;     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        r0.zzf = r9;     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        r0.zzg = r10;     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        r0.zza = r11;     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        r0.zzd = 1;     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        CancellableContinuationImpl r123 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r0), 1);     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        r123.initCancellability();     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        CronetEngine r22 = this.zzb;     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        zznc r3 = new zznc(r123);     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        ExecutorService r72 = this.zzc;     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        UrlRequest.Builder r23 = r22.newUrlRequestBuilder(r8, r3, r72);     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        r23.setHttpMethod(r9);     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        Iterator r92 = r11.entrySet().iterator();     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
    L20:
        if (r92.hasNext() == false) goto L22;
        Map.Entry r112 = (Map.Entry) r92.next();     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        r23.addHeader((String) r112.getKey(), (String) r112.getValue());     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        goto L20
    L22:
        if (r10 == null) goto L24;
        r23.setUploadDataProvider(UploadDataProviders.create(r10), r72);     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
    L24:
        r23.setTrafficStatsTag((int) Thread.currentThread().getId());     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        UrlRequest r73 = r23.build();     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        r73.start();     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        r123.invokeOnCancellation(new zzmz(r8, r73));     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        Object r74 = r123.getResult();     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
        if (r74 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L27;
        DebugProbesKt.probeCoroutineSuspended(r0);     // Catch: Exception -> L30 TimeoutCancellationException -> L33 zzhg -> L36
    L27:
        if (r74 != r13) goto L29;
        return r13;
    L29:
        return r74;
    L40:
        throw new zzhg(zzhf.zzc, zzhe.zzp, null, null, 12, null);
    L36:
        e = move-exception;
        throw e;
    L33:
        e = move-exception;
        throw new zzhg(zzhf.zzc, zzhe.zzb, e.getMessage(), null, 8, null);
    L30:
        e = move-exception;
        throw new zzhg(zzhf.zzc, zzhe.zzw, e.getMessage(), null, 8, null);
    L7:
        r0 = new zzmy(this, r12);
        goto L8
    }

    @Override // com.google.android.recaptcha.internal.zzmx
    public final Object zza(String r8, byte[] r9, Continuation r10) {
        return zze(r8, "POST", r9, MapsKt.mapOf(TuplesKt.to("Content-Type", "application/x-protobuffer")), r10);
    }

    @Override // com.google.android.recaptcha.internal.zzmx
    public final Object zzb(String r7, byte[] r8, Map r9, Continuation r10) {
        return zze("https://www.gstatic.com/recaptcha/verify_key/orcas/prod/android/verify_key.txt", "GET", null, r9, r10);
    }
}
