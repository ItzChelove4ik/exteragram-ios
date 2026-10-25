package androidx.camera.camera2.pipe.compat;

import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/compat/OpenCameraResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CameraStateOpener$tryOpenCamera$2$resultDeferred$1", f = "RetryingCameraStateOpener.kt", i = {}, l = {291}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class CameraStateOpener$tryOpenCamera$2$resultDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super OpenCameraResult>, Object> {
    final /* synthetic */ AndroidCameraState $cameraState;
    int label;

    public CameraStateOpener$tryOpenCamera$2$resultDeferred$1(AndroidCameraState r1, Continuation<? super CameraStateOpener$tryOpenCamera$2$resultDeferred$1> r2) {
        this.$cameraState = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
        return new CameraStateOpener$tryOpenCamera$2$resultDeferred$1(this.$cameraState, r2);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super OpenCameraResult> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super OpenCameraResult> r2) {
        return ((CameraStateOpener$tryOpenCamera$2$resultDeferred$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L8;
        if (r1 != 1) goto L6;
        ResultKt.throwOnFailure(r5);
    L11:
        CameraState r52 = (CameraState) r5;
        if ((r52 instanceof CameraStateOpen) == false) goto L16;
        return new OpenCameraResult(this.$cameraState, null, 2, null);
    L16:
        if ((r52 instanceof CameraStateClosing) == false) goto L20;
        this.$cameraState.close();
        return new OpenCameraResult(null, ((CameraStateClosing) r52).m463getCameraErrorCodemVEW8x0(), 1, null);
    L20:
        if ((r52 instanceof CameraStateClosed) == false) goto L24;
        this.$cameraState.close();
        return new OpenCameraResult(null, ((CameraStateClosed) r52).m462getCameraErrorCodemVEW8x0(), 1, null);
    L24:
        if ((r52 instanceof CameraStateUnopened) == false) goto L27;
        this.$cameraState.close();
        DexMaker$$ExternalSyntheticBUOutline0.m("Unexpected CameraState: ", r52);
        return null;
    L27:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r5);
        StateFlow<CameraState> r53 = this.$cameraState.getState();
        CameraStateOpener$tryOpenCamera$2$resultDeferred$1$result$1 r12 = new CameraStateOpener$tryOpenCamera$2$resultDeferred$1$result$1(null);
        this.label = 1;
        r5 = FlowKt.first(r53, r12, this);
        if (r5 != r0) goto L11;
        return r0;
    }
}
