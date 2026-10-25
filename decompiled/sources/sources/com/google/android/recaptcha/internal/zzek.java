package com.google.android.recaptcha.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* loaded from: classes5.dex */
final class zzek extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzeo zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzmg zzd;
    final /* synthetic */ zzaka zze;
    private /* synthetic */ Object zzf;

    public zzek(zzeo r1, String r2, zzmg r3, zzaka r4, Continuation r5) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        super(2, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r7, Continuation r8) {
        zzek r0 = new zzek(this.zzb, this.zzc, this.zzd, this.zze, r8);
        r0.zzf = r7;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzek) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.zza;
        ResultKt.throwOnFailure(r14);
        if (r1 != 0) goto L17;
        CoroutineScope r12 = (CoroutineScope) this.zzf;
        ArrayList r7 = new ArrayList();
        zzeo r142 = this.zzb;
        String r5 = this.zzc;
        r142.zzh().put(r5, r7);
        ArrayList r9 = new ArrayList();
        List r143 = zzeo.zzj(r142);
        ArrayList r10 = new ArrayList();
        Iterator r144 = r143.iterator();
    L7:
        if (r144.hasNext() == false) goto L11;
        Object r2 = r144.next();
        if (((zzev) r2).zzb() == false) goto L7;
        r10.add(r2);
        goto L7
    L11:
        int r145 = r10.size();
        int r22 = 0;
    L12:
        if (r22 >= r145) goto L14;
        r9.add(BuildersKt.launch$default(r12, null, null, new zzej(this.zzd, (zzev) r10.get(r22), r5, this.zze, r7, null), 3, null));
        r5 = r5;
        r22 = r22 + 1;
        goto L12
    L14:
        Job[] r146 = (Job[]) r9.toArray(new Job[0]);
        Job[] r147 = (Job[]) Arrays.copyOf(r146, r146.length);
        this.zza = 1;
        if (AwaitKt.joinAll(r147, this) != r0) goto L17;
        return r0;
    L17:
        Result.Companion r148 = Result.Companion;
        return Result.m2017boximpl(Result.m2018constructorimpl(zzeo.zzk(this.zzb, this.zzc)));
    }
}
