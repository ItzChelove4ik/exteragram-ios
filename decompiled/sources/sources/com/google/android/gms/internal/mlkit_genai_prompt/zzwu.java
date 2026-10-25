package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes4.dex */
final class zzwu extends SuspendLambda implements Function2 {
    Object zza;
    int zzb;
    final /* synthetic */ zzxi zzc;
    final /* synthetic */ zzxg zzd;
    final /* synthetic */ Context zze;

    public zzwu(zzxi r1, zzxg r2, Context r3, Continuation r4) {
        this.zzc = r1;
        this.zzd = r2;
        this.zze = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        return new zzwu(this.zzc, this.zzd, this.zze, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzwu) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.zzb != 0) goto L14;
        ResultKt.throwOnFailure(r10);
        zzxi r102 = this.zzc;
        zzxg r1 = this.zzd;
        Context r5 = this.zze;
        String r6 = r102.zza();
        File r7 = new File(zzxg.zzi(r1, r5), r102.zzb());
        if (r7.exists() == true) goto L13;
        StringBuilder r72 = new StringBuilder(String.valueOf(r6).length() + 51);     // Catch: IOException -> L10
        r72.append("Cache file not found for name: ");     // Catch: IOException -> L10
        r72.append(r6);     // Catch: IOException -> L10
        r72.append(". Cleaning up index.");     // Catch: IOException -> L10
        Log.w("CacheFileManager", r72.toString());     // Catch: IOException -> L10
        this.zza = r6;     // Catch: IOException -> L10
        this.zzb = 1;     // Catch: IOException -> L10
        r10 = zzxg.zzj(r1, r5, r6, this);     // Catch: IOException -> L10
        if (r10 != r0) goto L16;
        return r0;
    L16:
        ((Boolean) r10).getClass();     // Catch: IOException -> L10
        StringBuilder r02 = new StringBuilder(String.valueOf(r6).length() + 31);     // Catch: IOException -> L10
        r02.append("Cache file not found for name: ");     // Catch: IOException -> L10
        r02.append(r6);     // Catch: IOException -> L10
        throw new FileNotFoundException(r02.toString());     // Catch: IOException -> L10
    L13:
        return ParcelFileDescriptor.open(r7, 268435456);
    L14:
        r6 = (String) this.zza;
        ResultKt.throwOnFailure(r10);     // Catch: IOException -> L10
    L10:
        e = move-exception;
        Log.e("CacheFileManager", "Failed to open cache for reading", e);
        return null;
    }
}
