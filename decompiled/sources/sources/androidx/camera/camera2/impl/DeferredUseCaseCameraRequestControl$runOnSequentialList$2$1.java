package androidx.camera.camera2.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.DeferredUseCaseCameraRequestControl$runOnSequentialList$2$1", f = "DeferredUseCaseCameraRequestControl.kt", i = {}, l = {108, 110}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nDeferredUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeferredUseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/DeferredUseCaseCameraRequestControl$runOnSequentialList$2$1\n*L\n1#1,223:1\n*E\n"})
/* loaded from: classes3.dex */
public final class DeferredUseCaseCameraRequestControl$runOnSequentialList$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<Object>, Object> {
    final /* synthetic */ int $index;
    final /* synthetic */ Deferred<List<Deferred<Object>>> $submissionJob;
    int label;

    /* JADX WARN: Multi-variable type inference failed */
    public DeferredUseCaseCameraRequestControl$runOnSequentialList$2$1(Deferred<? extends List<? extends Deferred<Object>>> r1, int r2, Continuation<? super DeferredUseCaseCameraRequestControl$runOnSequentialList$2$1> r3) {
        this.$submissionJob = r1;
        this.$index = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new DeferredUseCaseCameraRequestControl$runOnSequentialList$2$1(this.$submissionJob, this.$index, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<Object> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<Object> r2) {
        return ((DeferredUseCaseCameraRequestControl$runOnSequentialList$2$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L11;
        if (r1 == 1) goto L10;
        if (r1 != 2) goto L8;
        ResultKt.throwOnFailure(r6);
        return r6;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L10:
        ResultKt.throwOnFailure(r6);
    L14:
        List r62 = (List) r6;
        if (this.$index >= r62.size()) goto L20;
        Deferred r63 = (Deferred) r62.get(this.$index);
        this.label = 2;
        Object r5 = r63.await(this);
        if (r5 != r0) goto L19;
    L18:
        return r0;
    L19:
        return r5;
    L20:
        return null;
    L11:
        ResultKt.throwOnFailure(r6);
        Deferred<List<Deferred<Object>>> r64 = this.$submissionJob;
        this.label = 1;
        r6 = r64.await(this);
        if (r6 != r0) goto L14;
        goto L14
    }
}
