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
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00030\u0002\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/Deferred;", "<anonymous>", "(Lkotlinx/coroutines/CoroutineScope;)Ljava/util/List;"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.camera.camera2.impl.DeferredUseCaseCameraRequestControl$issueSingleCaptureAsync$$inlined$runOnSequentialList$1", f = "DeferredUseCaseCameraRequestControl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nDeferredUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeferredUseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/DeferredUseCaseCameraRequestControl$runOnSequentialList$submissionJob$1\n+ 2 DeferredUseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/DeferredUseCaseCameraRequestControl\n*L\n1#1,223:1\n208#2:224\n*E\n"})
/* loaded from: classes3.dex */
public final class DeferredUseCaseCameraRequestControl$issueSingleCaptureAsync$$inlined$runOnSequentialList$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Deferred<? extends Void>>>, Object> {
    final /* synthetic */ int $captureMode$inlined;
    final /* synthetic */ List $captureSequence$inlined;
    final /* synthetic */ int $flashMode$inlined;
    final /* synthetic */ int $flashType$inlined;
    int label;
    final /* synthetic */ DeferredUseCaseCameraRequestControl this$0;

    public DeferredUseCaseCameraRequestControl$issueSingleCaptureAsync$$inlined$runOnSequentialList$1(DeferredUseCaseCameraRequestControl r1, Continuation r2, List r3, int r4, int r5, int r6) {
        this.this$0 = r1;
        this.$captureSequence$inlined = r3;
        this.$captureMode$inlined = r4;
        this.$flashType$inlined = r5;
        this.$flashMode$inlined = r6;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r8, Continuation<?> r9) {
        return new DeferredUseCaseCameraRequestControl$issueSingleCaptureAsync$$inlined$runOnSequentialList$1(this.this$0, r9, this.$captureSequence$inlined, this.$captureMode$inlined, this.$flashType$inlined, this.$flashMode$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super List<? extends Deferred<? extends Void>>> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super List<? extends Deferred<? extends Void>>> r2) {
        return ((DeferredUseCaseCameraRequestControl$issueSingleCaptureAsync$$inlined$runOnSequentialList$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L6;
        ResultKt.throwOnFailure(r4);
        return DeferredUseCaseCameraRequestControl.access$getOrCreateImpl(this.this$0).issueSingleCaptureAsync(this.$captureSequence$inlined, this.$captureMode$inlined, this.$flashType$inlined, this.$flashMode$inlined);
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
