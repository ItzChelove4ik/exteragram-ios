package com.google.android.recaptcha.internal;

import java.util.ArrayList;
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
final class zzco extends SuspendLambda implements Function2 {
    int zza;
    final /* synthetic */ zzct zzb;
    final /* synthetic */ String zzc;
    final /* synthetic */ zzmg zzd;
    final /* synthetic */ zzaka zze;
    final /* synthetic */ long zzf;
    private /* synthetic */ Object zzg;

    public zzco(zzct r1, String r2, zzmg r3, zzaka r4, long r5, Continuation r7) {
        this.zzb = r1;
        this.zzc = r2;
        this.zzd = r3;
        this.zze = r4;
        this.zzf = r5;
        super(2, r7);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r9, Continuation r10) {
        zzco r0 = new zzco(this.zzb, this.zzc, this.zzd, this.zze, this.zzf, r10);
        r0.zzg = r9;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzco) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r21) {
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = this.zza;
        ResultKt.throwOnFailure(r21);
        if (r2 == 0) goto L5;
        Object r22 = r21;
    L19:
        String r0 = this.zzc;
        zzakt r12 = zzaku.zza();
        r12.zza(r0);
        Iterator r02 = ((List) r22).iterator();
    L21:
        if (r02.hasNext() == false) goto L26;
        Object r23 = ((Result) r02.next()).m2027unboximpl();
        if (Result.m2025isSuccessimpl(r23) == false) goto L21;
        r12.zzq((zzaku) r23);
        goto L21
    L26:
        return (zzaku) r12.zzp();
    L5:
        CoroutineScope r3 = (CoroutineScope) this.zzg;
        List r24 = this.zzb.zza();
        ArrayList r9 = new ArrayList();
        Iterator r25 = r24.iterator();
    L7:
        if (r25.hasNext() == false) goto L12;
        Object r4 = r25.next();
        if (((zzcl) r4).zza() == false) goto L7;
        r9.add(r4);
        goto L7
    L12:
        if (r9.isEmpty() == true) goto L28;
        zzmg r11 = this.zzd;
        String r13 = this.zzc;
        zzaka r14 = this.zze;
        long r42 = this.zzf;
        ArrayList r26 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r9, 10));
        int r6 = r9.size();
        int r7 = 0;
    L14:
        if (r7 >= r6) goto L16;
        long r15 = r42;
        r26.add(BuildersKt.async$default(r3, null, null, new zzcn(r11, (zzcl) r9.get(r7), r13, r14, r15, null), 3, null));
        r6 = r6;
        r42 = r15;
        r7 = r7 + 1;
        goto L14
    L16:
        this.zza = 1;
        r22 = AwaitKt.awaitAll(r26, this);
        if (r22 != r1) goto L19;
        return r1;
    L28:
        throw new zzhg(zzhf.zzb, zzhe.zzr, null, null, 12, null);
    }
}
