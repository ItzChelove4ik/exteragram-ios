package com.google.android.gms.internal.mlkit_genai_prompt;

import android.content.Context;
import android.util.Log;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes4.dex */
final class zzwv extends SuspendLambda implements Function2 {
    /* synthetic */ Object zza;
    final /* synthetic */ String zzb;
    final /* synthetic */ zzxg zzc;
    final /* synthetic */ Context zzd;
    final /* synthetic */ zzxi zze;
    final /* synthetic */ Function1 zzf;

    public zzwv(String r1, zzxg r2, Context r3, zzxi r4, Function1 r5, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zzwv r0 = new zzwv(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, r9);
        r0.zza = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzwv) create((Map) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r7) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(r7);
        Map r72 = (Map) this.zza;
        String r0 = this.zzb;
        zzxi r1 = (zzxi) r72.get(r0);
        if (r1 == null) goto L5;
        StringBuilder r4 = new StringBuilder(String.valueOf(r0).length() + 31);
        r4.append("Overwriting cache with name '");
        r4.append(r0);
        r4.append("'.");
        Log.i("CacheFileManager", r4.toString());
        zzxg.zzh(this.zzc, this.zzd, r1);
    L5:
        zzxi r12 = this.zze;
        r72.put(r0, r12);
        Function1 r02 = this.zzf;
        if (r02 == null) goto L17;
        Iterator r03 = ((Collection) r02.invoke(r72)).iterator();
    L9:
        if (r03.hasNext() == false) goto L17;
        zzxi r3 = (zzxi) r03.next();
        if (Intrinsics.areEqual(r3.zza(), r12.zza()) == true) goto L12;
        zzxi r32 = (zzxi) r72.remove(r3.zza());
        if (r32 == null) goto L9;
        zzxg.zzh(this.zzc, this.zzd, r32);
        goto L9
    L12:
        Log.w("CacheFileManager", "Eviction policy attempts to delete cache being written: ".concat(String.valueOf(r3.zza())));
    L17:
        return Unit.INSTANCE;
    }
}
