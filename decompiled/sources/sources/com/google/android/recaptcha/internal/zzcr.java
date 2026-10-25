package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes5.dex */
final class zzcr extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzct zzb;
    final /* synthetic */ zzmg zzc;
    final /* synthetic */ long zzd;
    final /* synthetic */ zzakg zze;
    private /* synthetic */ Object zzf;

    public zzcr(zzct r1, zzmg r2, long r3, zzakg r5, Continuation r6) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r5;
        super(2, r6);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r8, Continuation r9) {
        zzcr r0 = new zzcr(this.zzb, this.zzc, this.zzd, this.zze, r9);
        r0.zzf = r8;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzcr) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r13) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r13);
        if (r1 != 0) goto L16;
        CoroutineScope r12 = (CoroutineScope) this.zzf;
        zzct r132 = this.zzb;
        if (r132.zza().isEmpty() == true) goto L8;
        List r133 = r132.zza();
        zzmg r3 = this.zzc;
        long r5 = this.zzd;
        zzakg r7 = this.zze;
        ArrayList r9 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r133, 10));
        Iterator r134 = r133.iterator();
    L11:
        if (r134.hasNext() == false) goto L13;
        zzcq r2 = new zzcq(r3, (zzcl) r134.next(), r5, r7, null);
        zzakg r8 = r7;
        r9.add(BuildersKt.async$default(r12, null, null, r2, 3, null));
        r3 = r3;
        r7 = r8;
        r5 = r5;
        goto L11
    L13:
        this.zza = 1;
        r13 = AwaitKt.awaitAll(r9, this);
        if (r13 != r0) goto L16;
        return r0;
    L8:
        return Unit.INSTANCE;
    L16:
        List r135 = (List) r13;
        if ((r135 instanceof Collection) == true) goto L19;
    L20:
        Iterator r122 = r135.iterator();
    L22:
        if (r122.hasNext() == false) goto L28;
        if (Result.m2024isFailureimpl(((Result) r122.next()).m2027unboximpl()) == true) goto L22;
        return Unit.INSTANCE;
    L28:
        throw new zzhg(zzhf.zzb, zzhe.zzr, null, null, 12, null);
    L19:
        if (r135.isEmpty() == true) goto L28;
        goto L20
    }
}
