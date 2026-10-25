package com.google.android.gms.internal.mlkit_genai_prompt;

import android.util.Log;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.genai.common.DownloadCallback;
import com.google.mlkit.genai.common.GenAiException;
import com.google.mlkit.genai.common.StreamingCallback;
import java.util.concurrent.ExecutorService;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlinx.coroutines.guava.ListenableFutureKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes4.dex */
public abstract class zzaur extends zzaun {
    public zzaur(ExecutorService r1, int r2, int r3, zzaty r4) {
        super(r1, r2, -1, r4);
    }

    public static /* synthetic */ Object zzA(zzaur r4, Continuation r5) {
        if ((r5 instanceof zzaup) == false) goto L7;
        zzaup r0 = (zzaup) r5;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r52 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r52);
    L17:
        return r52;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r52);
        ListenableFuture r42 = r4.zzi();
        r0.zzc = 1;
        r52 = ListenableFutureKt.await(r42, r0);
        if (r52 != r12) goto L17;
        return r12;
    L7:
        r0 = new zzaup(r4, r5);
        goto L8
    }

    public static /* synthetic */ Object zzB(zzaur r4, Continuation r5) {
        if ((r5 instanceof zzauo) == false) goto L7;
        zzauo r0 = (zzauo) r5;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r52 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L15;
        if (r2 != 1) goto L13;
        ResultKt.throwOnFailure(r52);     // Catch: GenAiException -> L20
    L23:
        return r52;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r52);
        ListenableFuture r42 = r4.zzj();     // Catch: GenAiException -> L20
        r0.zzc = 1;     // Catch: GenAiException -> L20
        r52 = ListenableFutureKt.await(r42, r0);     // Catch: GenAiException -> L20
        if (r52 != r12) goto L23;
        return r12;
    L20:
        e = move-exception;
        Log.w("GenAiProcessorKt", "checkFeatureStatus failed", e);
        return Boxing.boxInt(0);
    L7:
        r0 = new zzauo(r4, r5);
        goto L8
    }

    public static /* synthetic */ Object zzD(zzaur r0, DownloadCallback r1, Continuation r2) {
        Object r02 = ListenableFutureKt.await(r0.zzk(r1), r2);
        if (r02 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r02;
    L6:
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object zzF(zzaur r0, Continuation r1) {
        Object r02 = ListenableFutureKt.await(r0.zzl(), r1);
        if (r02 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r02;
    L6:
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object zzH(zzaur r4, Object r5, Continuation r6) {
        if ((r6 instanceof zzauq) == false) goto L7;
        zzauq r0 = (zzauq) r6;
        int r1 = r0.zzc;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.zzc = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.zza;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.zzc;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r62);
    L17:
        return r62;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r62);
        ListenableFuture r42 = r4.zzm(r5);
        r0.zzc = 1;
        r62 = ListenableFutureKt.await(r42, r0);
        if (r62 != r12) goto L17;
        return r12;
    L7:
        r0 = new zzauq(r4, r6);
        goto L8
    }

    public final Object zzC(DownloadCallback r1, Continuation r2) {
        return zzD(this, r1, r2);
    }

    public final Object zzE(Continuation r1) {
        return zzF(this, r1);
    }

    public final Object zzG(Object r1, Continuation r2) {
        return zzH(this, r1, r2);
    }

    public final Object zzI(Object r1, StreamingCallback r2, Continuation r3) {
        return ListenableFutureKt.await(zzn(r1, r2), r3);
    }

    public Object zza(Continuation r1) {
        return zzB(this, r1);
    }

    public final Object zzz(Continuation r1) {
        return zzA(this, r1);
    }
}
