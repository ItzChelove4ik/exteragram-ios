package androidx.camera.camera2.impl;

import androidx.camera.camera2.adapter.CoroutineAdaptersKt;
import androidx.camera.core.CameraControl;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", _UrlKt.FRAGMENT_ENCODE_SET, "<anonymous>", "(Lkotlinx/coroutines/CoroutineScope;)V"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.camera.camera2.impl.LowLightBoostControl$setLowLightBoostAsync$$inlined$confineLaunch$1", f = "LowLightBoostControl.kt", i = {}, l = {201}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseThreads.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads$confineLaunch$1\n+ 2 LowLightBoostControl.kt\nandroidx/camera/camera2/impl/LowLightBoostControl\n*L\n1#1,200:1\n167#2,56:201\n*E\n"})
/* loaded from: classes3.dex */
public final class LowLightBoostControl$setLowLightBoostAsync$$inlined$confineLaunch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $cancelPreviousTask$inlined;
    final /* synthetic */ boolean $lowLightBoost$inlined;
    final /* synthetic */ CompletableDeferred $signal$inlined;
    int label;
    final /* synthetic */ LowLightBoostControl this$0;

    public LowLightBoostControl$setLowLightBoostAsync$$inlined$confineLaunch$1(Continuation r1, LowLightBoostControl r2, CompletableDeferred r3, boolean r4, boolean r5) {
        this.this$0 = r2;
        this.$signal$inlined = r3;
        this.$lowLightBoost$inlined = r4;
        this.$cancelPreviousTask$inlined = r5;
        super(2, r1);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
        return new LowLightBoostControl$setLowLightBoostAsync$$inlined$confineLaunch$1(r8, this.this$0, this.$signal$inlined, this.$lowLightBoost$inlined, this.$cancelPreviousTask$inlined);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((LowLightBoostControl$setLowLightBoostAsync$$inlined$confineLaunch$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        Integer r2 = null;
        if (r1 == 0) goto L8;
        if (r1 != 1) goto L6;
        ResultKt.throwOnFailure(r6);
    L13:
        boolean r62 = ((Boolean) r6).booleanValue();
    L15:
        LowLightBoostControl r02 = this.this$0;
        if (r62 == false) goto L18;
        LowLightBoostControl.access$setLiveDataValue(r02, LowLightBoostControl.access$get_lowLightBoostState$p(r02), -1);
        LowLightBoostControl.access$createFailureResult(this.this$0, this.$signal$inlined, new IllegalStateException("Low Light Boost is disabled when expected frame rate range exceeds 30."));
    L39:
        return Unit.INSTANCE;
    L18:
        LowLightBoostControl.access$setLowLightBoostOn$p(r02, this.$lowLightBoost$inlined);
        if (this.$lowLightBoost$inlined == true) goto L22;
        LowLightBoostControl r63 = this.this$0;
        LowLightBoostControl.access$setLiveDataValue(r63, LowLightBoostControl.access$get_lowLightBoostState$p(r63), -1);
    L22:
        if (this.this$0.getRequestControl() != null) goto L24;
    L37:
        LowLightBoostControl.access$createFailureResult(this.this$0, this.$signal$inlined, new CameraControl.OperationCanceledException("Camera is not active."));
        Unit r5 = Unit.INSTANCE;
        goto L39
    L24:
        if (this.$lowLightBoost$inlined == false) goto L26;
        LowLightBoostControl r64 = this.this$0;
        LowLightBoostControl.access$setLiveDataValue(r64, LowLightBoostControl.access$get_lowLightBoostState$p(r64), 0);
    L26:
        boolean r65 = this.$cancelPreviousTask$inlined;
        LowLightBoostControl r03 = this.this$0;
        if (r65 == false) goto L29;
        LowLightBoostControl.access$stopRunningTaskInternal(r03);
    L32:
        LowLightBoostControl.access$set_updateSignal$p(this.this$0, this.$signal$inlined);
        State3AControl r66 = LowLightBoostControl.access$getState3AControl$p(this.this$0);
        if (this.$lowLightBoost$inlined == false) goto L35;
        r2 = Boxing.boxInt(6);
    L35:
        CoroutineAdaptersKt.propagateTo(r66.setPreferredAeModeAsync(r2), this.$signal$inlined);
        CompletableDeferred r67 = this.$signal$inlined;
        if (r67.invokeOnCompletion(new LowLightBoostControl$setLowLightBoostAsync$2$1$2(r67, this.this$0)) != null) goto L39;
    L29:
        CompletableDeferred r68 = LowLightBoostControl.access$get_updateSignal$p(r03);
        if (r68 == null) goto L32;
        CoroutineAdaptersKt.propagateTo(this.$signal$inlined, r68);
        goto L32
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r6);
        Deferred<Boolean> r69 = this.this$0.getCheckFrameRateJob$camera_camera2();
        if (r69 == null) goto L14;
        this.label = 1;
        r6 = r69.await(this);
        if (r6 != r0) goto L13;
        return r0;
    L14:
        r62 = false;
        goto L15
    }
}
