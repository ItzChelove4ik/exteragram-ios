package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONArray;
import org.json.JSONException;

/* loaded from: classes4.dex */
final class zzwz extends SuspendLambda implements Function2 {
    final /* synthetic */ zzxg zza;
    final /* synthetic */ Context zzb;

    public zzwz(zzxg r1, Context r2, Continuation r3) {
        this.zza = r1;
        this.zzb = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r2, Continuation r3) {
        return new zzwz(this.zza, this.zzb, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzwz) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r4);
        zzxg r42 = this.zza;
        File r3 = zzxg.zzq(r42, this.zzb);
        if (r3.exists() == true) goto L20;
        return MapsKt.emptyMap();
    L20:
        String r1 = FilesKt.readText$default(r3, null, 1, null);     // Catch: JSONException -> L11 IOException -> L13
        if (StringsKt.isBlank(r1) == true) goto L16;
        return zzxg.zzr(r42, new JSONArray(r1));
    L16:
        return MapsKt.emptyMap();
    L13:
        e = move-exception;
        Log.e("CacheFileManager", "Failed to read cache index file.", e);
        return MapsKt.emptyMap();
    L11:
        e = move-exception;
        Log.e("CacheFileManager", "Cache index file is corrupted.", e);
        r3.delete();
        return MapsKt.emptyMap();
    }
}
