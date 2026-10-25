package com.google.android.gms.internal.mlkit_genai_prompt;

import android.util.Log;
import com.google.mlkit.genai.common.DownloadCallback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* loaded from: classes4.dex */
final class zzya extends SuspendLambda implements Function2 {
    Object zza;
    Object zzb;
    Object zzc;
    Object zzd;
    Object zze;
    Object zzf;
    int zzg;
    int zzh;
    final /* synthetic */ zzyd zzi;
    final /* synthetic */ DownloadCallback zzj;
    private /* synthetic */ Object zzk;

    public zzya(zzyd r1, DownloadCallback r2, Continuation r3) {
        this.zzi = r1;
        this.zzj = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object r3, Continuation r4) {
        zzya r0 = new zzya(this.zzi, this.zzj, r4);
        r0.zzk = r3;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* bridge */ /* synthetic */ Object invoke(Object r1, Object r2) {
        return ((zzya) create((CoroutineScope) r1, (Continuation) r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x00dd -> B:28:0x00e2). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r23) {
        Object r2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.zzh;
        if (r0 == 0) goto L15;
        if (r0 == 1) goto L14;
        if (r0 == 2) goto L13;
        if (r0 == 3) goto L8;
        Iterator r02 = (Iterator) this.zzk;
        ResultKt.throwOnFailure(r23);
    L69:
        if (r02.hasNext() == false) goto L74;
        Job r3 = (Job) r02.next();
        this.zzk = r02;
        this.zza = null;
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzf = null;
        this.zzh = 4;
        if (r3.join(this) != r2) goto L69;
    L75:
        return r2;
    L74:
        return Unit.INSTANCE;
    L8:
        int r32 = this.zzg;
        List r4 = (List) this.zzd;
        List r5 = (List) this.zzc;
        zzaur r8 = (zzaur) this.zzb;
        AtomicInteger r9 = (AtomicInteger) this.zza;
        CoroutineScope r10 = (CoroutineScope) this.zzk;
        ResultKt.throwOnFailure(r23);     // Catch: Exception -> L11
    L10:
        int r13 = r32;
        AtomicInteger r12 = r9;
        CoroutineScope r03 = r10;
    L62:
        Iterator r33 = r5.iterator();
    L64:
        if (r33.hasNext() == false) goto L66;
        CoroutineScope r14 = r03;
        r4.add(BuildersKt.launch$default(r14, null, null, new zzxx(this.zzi, (zzaur) r33.next(), this.zzj, r12, r13, null), 3, null));
        r03 = r14;
        goto L64
    L66:
        List r52 = r4;
    L67:
        r02 = r52.iterator();
    L11:
        e = e;
    L54:
        Log.w("DownloadManager", "Primary download start failed: ".concat(String.valueOf(e.getMessage())));
        if (Intrinsics.areEqual(r8, zzyd.zze(this.zzi)) == false) goto L10;
        Iterator r04 = r4.iterator();
    L58:
        if (r04.hasNext() == false) goto L61;
        Job.cancel$default((Job) r04.next(), null, 1, null);
        goto L58
    L61:
        return Unit.INSTANCE;
    L13:
        int r05 = this.zzg;
        zzaur r82 = (zzaur) this.zzf;
        Object r92 = this.zze;
        Iterator r102 = (Iterator) this.zzd;
        Collection r11 = (Collection) this.zzc;
        zzyd r122 = (zzyd) this.zzb;
        AtomicInteger r132 = (AtomicInteger) this.zza;
        CoroutineScope r142 = (CoroutineScope) this.zzk;
        ResultKt.throwOnFailure(r23);
        Object r15 = r23;
    L29:
        if (((Number) r15).intValue() == 1) goto L31;
    L33:
        CoroutineScope r83 = r142;
    L23:
        if (r102.hasNext() == false) goto L34;
        r92 = r102.next();
        zzaur r143 = (zzaur) r92;
        this.zzk = r83;
        this.zza = r132;
        this.zzb = r122;
        this.zzc = r11;
        this.zzd = r102;
        this.zze = r92;
        this.zzf = r143;
        this.zzg = r05;
        this.zzh = 2;
        r15 = r143.zza(this);
        if (r15 == r2) goto L75;
        r142 = r83;
        r82 = r143;
        goto L29
    L34:
        List r112 = (List) r11;
        if (r05 == 0) goto L37;
        int r42 = 1;
    L38:
        int r19 = r42 + r112.size();
        if (r19 != 0) goto L42;
        this.zzj.onDownloadCompleted();
        return Unit.INSTANCE;
    L42:
        CompletableDeferred r17 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        if (r05 == 0) goto L46;
        zzaur r06 = zzyd.zze(this.zzi);
        List r43 = r112;
    L45:
        zzaur r152 = r06;
        AtomicInteger r18 = r132;
        CoroutineScope r144 = r83;
        r52 = CollectionsKt.mutableListOf(new Job[]{BuildersKt.launch$default(r144, null, null, new zzxz(this.zzi, r152, this.zzj, r17, r18, r19, null), 3, null)});
        if (r43.isEmpty() == true) goto L67;
        this.zzk = r144;     // Catch: Exception -> L52
        this.zza = r18;     // Catch: Exception -> L52
        this.zzb = r152;     // Catch: Exception -> L52
        this.zzc = r43;     // Catch: Exception -> L52
        this.zzd = r52;     // Catch: Exception -> L52
        this.zze = null;     // Catch: Exception -> L52
        this.zzf = null;     // Catch: Exception -> L52
        this.zzg = r19;     // Catch: Exception -> L52
        this.zzh = 3;     // Catch: Exception -> L52
        if (r17.await(this) == r2) goto L75;
        r5 = r43;
        r4 = r52;
        r12 = r18;
        r03 = r144;
        r13 = r19;
    L52:
        e = e;
        r5 = r43;
        r4 = r52;
        r32 = r19;
        r8 = r152;
        r9 = r18;
        r10 = r144;
        goto L54
    L46:
        r06 = (zzaur) CollectionsKt.first(r112);
        r43 = CollectionsKt.drop(r112, 1);
        goto L45
    L37:
        r42 = 0;
        goto L38
    L31:
        if (Intrinsics.areEqual(zzyd.zzg(r122).get(r82), Boxing.boxBoolean(true)) == true) goto L33;
        r11.add(r92);
        goto L33
    L14:
        AtomicInteger r07 = (AtomicInteger) this.zza;
        r83 = (CoroutineScope) this.zzk;
        ResultKt.throwOnFailure(r23);
        Object r93 = r23;
    L18:
        if (((Number) r93).intValue() != 1) goto L20;
        int r94 = 1;
    L21:
        zzyd r103 = this.zzi;
        List r113 = zzyd.zzf(r103);
        ArrayList r123 = new ArrayList();
        r122 = r103;
        r102 = r113.iterator();
        r11 = r123;
        r132 = r07;
        r05 = r94;
        goto L23
    L20:
        r94 = 0;
        goto L21
    L15:
        ResultKt.throwOnFailure(r23);
        r83 = (CoroutineScope) this.zzk;
        r07 = new AtomicInteger(0);
        zzaur r95 = zzyd.zze(this.zzi);
        this.zzk = r83;
        this.zza = r07;
        this.zzh = 1;
        r93 = r95.zza(this);
        if (r93 == r2) goto L75;
        goto L75
    }
}
