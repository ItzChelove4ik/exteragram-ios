package androidx.camera.camera2.impl;

import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.Lock3ABehavior;
import androidx.camera.camera2.pipe.Result3A;
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

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0002\u001a\u00028\u0000\"\u0004\b\u0000\u0010\u0000*\u00020\u0001H\n"}, d2 = {"T", "Lkotlinx/coroutines/CoroutineScope;", "<anonymous>"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.camera.camera2.impl.DeferredUseCaseCameraRequestControl$startFocusAndMeteringAsync-NxRnBj4$$inlined$runOnSequential$1", f = "DeferredUseCaseCameraRequestControl.kt", i = {}, l = {90}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nDeferredUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DeferredUseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/DeferredUseCaseCameraRequestControl$runOnSequential$2\n+ 2 DeferredUseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/DeferredUseCaseCameraRequestControl\n*L\n1#1,223:1\n179#2,10:224\n*E\n"})
/* renamed from: androidx.camera.camera2.impl.DeferredUseCaseCameraRequestControl$startFocusAndMeteringAsync-NxRnBj4$$inlined$runOnSequential$1, reason: invalid class name */
/* loaded from: classes3.dex */
public final class DeferredUseCaseCameraRequestControl$startFocusAndMeteringAsyncNxRnBj4$$inlined$runOnSequential$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Result3A>, Object> {
    final /* synthetic */ Lock3ABehavior $aeLockBehavior$inlined;
    final /* synthetic */ List $aeRegions$inlined;
    final /* synthetic */ Lock3ABehavior $afLockBehavior$inlined;
    final /* synthetic */ List $afRegions$inlined;
    final /* synthetic */ AeMode $afTriggerStartAeMode$inlined;
    final /* synthetic */ Lock3ABehavior $awbLockBehavior$inlined;
    final /* synthetic */ List $awbRegions$inlined;
    final /* synthetic */ long $timeLimitNs$inlined;
    int label;
    final /* synthetic */ DeferredUseCaseCameraRequestControl this$0;

    public DeferredUseCaseCameraRequestControl$startFocusAndMeteringAsyncNxRnBj4$$inlined$runOnSequential$1(DeferredUseCaseCameraRequestControl r1, Continuation r2, List r3, List r4, List r5, Lock3ABehavior r6, Lock3ABehavior r7, Lock3ABehavior r8, AeMode r9, long r10) {
        this.this$0 = r1;
        this.$aeRegions$inlined = r3;
        this.$afRegions$inlined = r4;
        this.$awbRegions$inlined = r5;
        this.$aeLockBehavior$inlined = r6;
        this.$afLockBehavior$inlined = r7;
        this.$awbLockBehavior$inlined = r8;
        this.$afTriggerStartAeMode$inlined = r9;
        this.$timeLimitNs$inlined = r10;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r13, Continuation<?> r14) {
        return new DeferredUseCaseCameraRequestControl$startFocusAndMeteringAsyncNxRnBj4$$inlined$runOnSequential$1(this.this$0, r14, this.$aeRegions$inlined, this.$afRegions$inlined, this.$awbRegions$inlined, this.$aeLockBehavior$inlined, this.$afLockBehavior$inlined, this.$awbLockBehavior$inlined, this.$afTriggerStartAeMode$inlined, this.$timeLimitNs$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Result3A> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Result3A> r2) {
        return ((DeferredUseCaseCameraRequestControl$startFocusAndMeteringAsyncNxRnBj4$$inlined$runOnSequential$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L9;
        if (r1 != 1) goto L7;
        ResultKt.throwOnFailure(r14);
        return r14;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r14);
        Deferred<Result3A> r142 = DeferredUseCaseCameraRequestControl.access$getOrCreateImpl(this.this$0).mo70startFocusAndMeteringAsyncNxRnBj4(this.$aeRegions$inlined, this.$afRegions$inlined, this.$awbRegions$inlined, this.$aeLockBehavior$inlined, this.$afLockBehavior$inlined, this.$awbLockBehavior$inlined, this.$afTriggerStartAeMode$inlined, this.$timeLimitNs$inlined);
        this.label = 1;
        Object r13 = r142.await(this);
        if (r13 != r0) goto L12;
        return r0;
    L12:
        return r13;
    }
}
