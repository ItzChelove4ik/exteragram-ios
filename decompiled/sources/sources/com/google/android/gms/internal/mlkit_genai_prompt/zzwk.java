package com.google.android.gms.internal.mlkit_genai_prompt;

import android.util.Log;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.prompt.CachedContext;
import com.google.mlkit.genai.prompt.CreateCachedContextRequest;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;

/* loaded from: classes4.dex */
final class zzwk extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzaal zzd;
    final /* synthetic */ zzwl zze;
    final /* synthetic */ CreateCachedContextRequest zzf;
    private /* synthetic */ Object zzg;

    public zzwk(zzaal r1, zzwl r2, CreateCachedContextRequest r3, Continuation r4) {
        this.zzd = r1;
        this.zze = r2;
        this.zzf = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r4, Continuation r5) {
        zzwk r0 = new zzwk(this.zzd, this.zze, this.zzf, r5);
        r0.zzg = r4;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzwk) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r21) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = this.zzc;
        if (r2 == 0) goto L20;
        if (r2 == 1) goto L12;
        zzpk r22 = (zzpk) this.zza;
        String r6 = (String) this.zzg;
        ResultKt.throwOnFailure(r21);     // Catch: Throwable -> L8 GenAiException -> L10
        zzpk r5 = r22;
        Object r23 = r21;
    L65:
    L37:
        e = e;
        r22 = r5;
    L51:
        r22.zzi(e);     // Catch: Throwable -> L8
        throw e;     // Catch: Throwable -> L8
    L31:
        if (((Boolean) r23).booleanValue() == false) goto L39;
        CreateCachedContextRequest r02 = this.zzf;     // Catch: Throwable -> L8 GenAiException -> L37
        CachedContext r24 = CachedContext.Companion.zza(r02.getName());     // Catch: Throwable -> L8 GenAiException -> L37
        zzwl r1 = this.zze;
        zzwl.zzg(r1).remove(r02);
        if (((zzpk) zzwl.zzh(r1).remove(r02)) != null) goto L36;
        Log.w("CacheCreationProcessor", "Future not found in map for request with prefix: ".concat(String.valueOf(r6)));
    L36:
        return r24;
    L39:
        StringBuilder r9 = new StringBuilder(String.valueOf(r6).length() + 34);     // Catch: Throwable -> L8 GenAiException -> L37
        r9.append("Cache creation failed for prefix: ");     // Catch: Throwable -> L8 GenAiException -> L37
        r9.append(r6);     // Catch: Throwable -> L8 GenAiException -> L37
        throw new GenAiException(r9.toString(), null, -103);     // Catch: Throwable -> L8 GenAiException -> L37
    L10:
        e = e;
    L8:
        th = th;
    L53:
        zzwl r25 = this.zze;
        CreateCachedContextRequest r12 = this.zzf;
        zzwl.zzg(r25).remove(r12);
        if (((zzpk) zzwl.zzh(r25).remove(r12)) != null) goto L56;
        Log.w("CacheCreationProcessor", "Future not found in map for request with prefix: ".concat(String.valueOf(r6)));
    L56:
        throw th;
    L12:
        Deferred r26 = (Deferred) this.zzb;
        zzpk r62 = (zzpk) this.zza;
        String r92 = (String) this.zzg;
        ResultKt.throwOnFailure(r21);     // Catch: Throwable -> L15 GenAiException -> L17
        r5 = r62;
        Object r63 = r21;
    L63:
        ((Boolean) r63).getClass();     // Catch: Throwable -> L15 GenAiException -> L42
        this.zzg = r92;     // Catch: Throwable -> L15 GenAiException -> L42
        this.zza = r5;     // Catch: Throwable -> L15 GenAiException -> L42
        this.zzb = null;     // Catch: Throwable -> L15 GenAiException -> L42
        this.zzc = 2;     // Catch: Throwable -> L15 GenAiException -> L42
        r23 = r26.await(this);     // Catch: Throwable -> L15 GenAiException -> L42
        if (r23 == r0) goto L44;
        r6 = r92;
    L44:
        return r0;
    L42:
        e = e;
        r22 = r5;
    L17:
        e = e;
        r22 = r62;
    L15:
        th = th;
        r6 = r92;
        goto L53
    L20:
        ResultKt.throwOnFailure(r21);
        CoroutineScope r93 = (CoroutineScope) this.zzg;
        zzaal r27 = this.zzd;
        zzwl r15 = this.zze;
        String r10 = r27.zza();
        ConcurrentHashMap r11 = zzwl.zzg(r15);
        CreateCachedContextRequest r122 = this.zzf;
        r11.put(r122, r27);
        r22 = zzpk.zza();
        if (zzwl.zzh(r15).putIfAbsent(r122, r22) != null) goto L58;
        Deferred r94 = BuildersKt.async$default(r93, null, null, new zzwi(r22, null), 3, null);
        r94.invokeOnCompletion(new zzwj(r22));
        this.zzg = r10;     // Catch: Throwable -> L45 GenAiException -> L47
        this.zza = r22;     // Catch: Throwable -> L45 GenAiException -> L47
        this.zzb = r94;     // Catch: Throwable -> L45 GenAiException -> L47
        this.zzc = 1;     // Catch: Throwable -> L45 GenAiException -> L47
        r63 = r15.zzI(r122, null, this);     // Catch: Throwable -> L45 GenAiException -> L47
        if (r63 == r0) goto L44;
        r5 = r22;
        r26 = r94;
        r92 = r10;
        goto L63
    L47:
        e = e;
    L45:
        th = th;
        r6 = r10;
        goto L53
    L58:
        throw new GenAiException("Cache creation already in progress for prefix: ".concat(String.valueOf(r10)), null, -103);
    }
}
