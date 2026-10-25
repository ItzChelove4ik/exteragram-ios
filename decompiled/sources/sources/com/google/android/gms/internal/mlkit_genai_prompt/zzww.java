package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import java.io.File;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes4.dex */
final class zzww extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    int zzc;
    final /* synthetic */ zzxi zzd;
    final /* synthetic */ zzxg zze;
    final /* synthetic */ Context zzf;
    final /* synthetic */ Function1 zzg;

    public zzww(zzxi r1, zzxg r2, Context r3, Function1 r4, Continuation r5) {
        this.zzd = r1;
        this.zze = r2;
        this.zzf = r3;
        this.zzg = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        return new zzww(this.zzd, this.zze, this.zzf, this.zzg, r8);
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzww) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.zzc;
        if (r0 == 0) goto L7;
        if (r0 == 1) goto L6;
        ResultKt.throwOnFailure(r12);
    L17:
        ((Boolean) r12).getClass();
        return null;
    L6:
        File r02 = (File) this.zzb;
        String r2 = (String) this.zza;
        ResultKt.throwOnFailure(r12);
    L20:
        return ParcelFileDescriptor.open(r02, 671088640);
    L13:
        e = move-exception;
        Log.e("CacheFileManager", "Failed to open cache for writing", e);
        zzxg r122 = this.zze;
        Context r03 = this.zzf;
        this.zza = null;
        this.zzb = null;
        this.zzc = 2;
        r12 = zzxg.zzj(r122, r03, r2, this);
        if (r12 != r1) goto L17;
    L19:
        return r1;
    L7:
        ResultKt.throwOnFailure(r12);
        zzxi r8 = this.zzd;
        zzxg r6 = this.zze;
        Context r7 = this.zzf;
        String r5 = r8.zza();
        String r123 = r8.zzb();
        r02 = new File(zzxg.zzi(r6, r7), r123);
        zzwv r4 = new zzwv(r5, r6, r7, r8, this.zzg, null);
        this.zza = r5;
        this.zzb = r02;
        this.zzc = 1;
        if (zzxg.zzk(r6, r7, r4, this) == r1) goto L19;
        r2 = r5;
        goto L20
    }
}
