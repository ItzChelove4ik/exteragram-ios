package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONArray;

/* loaded from: classes4.dex */
final class zzxf extends SuspendLambda implements Function2 {
    final /* synthetic */ zzxg zza;
    final /* synthetic */ Context zzb;
    final /* synthetic */ Map zzc;

    public zzxf(zzxg r1, Context r2, Map r3, Continuation r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzxf(this.zza, this.zzb, this.zzc, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzxf) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r7);
        File r72 = zzxg.zzq(this.zza, this.zzb);
        JSONArray r0 = new JSONArray();
        Iterator r6 = this.zzc.entrySet().iterator();
    L4:
        if (r6.hasNext() == false) goto L15;
        Map.Entry r1 = (Map.Entry) r6.next();
        String r3 = (String) r1.getKey();
        zzxi r12 = (zzxi) r1.getValue();
        if (Intrinsics.areEqual(r3, r12.zza()) == false) goto L7;
        r0.put(r12.zzc());
        goto L4
    L7:
        String r13 = r12.zza();
        int r4 = String.valueOf(r3).length();
        StringBuilder r5 = new StringBuilder(((r4 + 25) + String.valueOf(r13).length()) + 24);
        r5.append("IDs in key (");
        r5.append(r3);
        r5.append(") and value (");
        r5.append(r13);
        r5.append(") do not match. Ignored.");
        Log.w("CacheFileManager", r5.toString());
        goto L4
    L15:
        FilesKt.writeText$default(r72, r0.toString(), null, 2, null);     // Catch: IOException -> L12
        return Unit.INSTANCE;
    L12:
        e = move-exception;
        Log.e("CacheFileManager", "Failed to write cache index file.", e);
        throw e;
    }
}
