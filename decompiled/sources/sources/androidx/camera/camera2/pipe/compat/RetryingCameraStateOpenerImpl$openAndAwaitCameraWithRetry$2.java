package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.Log;
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
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/compat/AwaitOpenCameraResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2", f = "RetryingCameraStateOpener.kt", i = {1}, l = {497, 503}, m = "invokeSuspend", n = {"androidCameraState"}, s = {"L$0"}, v = 1)
@SourceDebugExtension({"SMAP\nRetryingCameraStateOpener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,665:1\n82#2,2:666\n59#2,2:668\n82#2,2:670\n*S KotlinDebug\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2\n*L\n499#1:666,2\n505#1:668,2\n508#1:670,2\n*E\n"})
/* loaded from: classes3.dex */
public final class RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super AwaitOpenCameraResult>, Object> {
    final /* synthetic */ Camera2DeviceCloser $camera2DeviceCloser;
    final /* synthetic */ String $cameraId;
    Object L$0;
    int label;
    final /* synthetic */ RetryingCameraStateOpenerImpl this$0;

    public RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2(RetryingCameraStateOpenerImpl r1, String r2, Camera2DeviceCloser r3, Continuation<? super RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2> r4) {
        this.this$0 = r1;
        this.$cameraId = r2;
        this.$camera2DeviceCloser = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        return new RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2(this.this$0, this.$cameraId, this.$camera2DeviceCloser, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super AwaitOpenCameraResult> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super AwaitOpenCameraResult> r2) {
        return ((RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r14) {
        Object r7 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r0 = this.label;
        if (r0 == 0) goto L10;
        if (r0 == 1) goto L9;
        if (r0 != 2) goto L7;
        AndroidCameraState r02 = (AndroidCameraState) this.L$0;
        ResultKt.throwOnFailure(r14);
        Object r1 = r14;
    L23:
        CameraState r12 = (CameraState) r1;
        if ((r12 instanceof CameraStateOpen) == false) goto L30;
        Log r2 = Log.INSTANCE;
        String r3 = this.$cameraId;
        if (r2.getINFO_LOGGABLE() == false) goto L29;
        android.util.Log.i("CXCP", CameraId.m232toStringimpl(r3) + " opened successfully.");
    L29:
        return new AwaitOpenCameraResult(((CameraStateOpen) r12).getCameraDevice(), r02);
    L30:
        Log r03 = Log.INSTANCE;
        String r13 = this.$cameraId;
        if (r03.getERROR_LOGGABLE() == false) goto L34;
        android.util.Log.e("CXCP", "Failed to open " + CameraId.m232toStringimpl(r13) + '!');
    L34:
        return new AwaitOpenCameraResult(null, null);
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r14);
        Object r04 = r14;
    L13:
        r02 = ((OpenCameraResult) r04).getCameraState();
        if (r02 != null) goto L20;
        Log r05 = Log.INSTANCE;
        String r15 = this.$cameraId;
        if (r05.getERROR_LOGGABLE() == false) goto L19;
        android.util.Log.e("CXCP", "Failed to open " + CameraId.m232toStringimpl(r15) + '!');
    L19:
        return new AwaitOpenCameraResult(null, null);
    L20:
        StateFlow<CameraState> r16 = r02.getState();
        RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2$cameraState$1 r22 = new RetryingCameraStateOpenerImpl$openAndAwaitCameraWithRetry$2$cameraState$1(null);
        this.L$0 = r02;
        this.label = 2;
        r1 = FlowKt.first(r16, r22, this);
        if (r1 != r7) goto L23;
    L22:
        return r7;
    L10:
        ResultKt.throwOnFailure(r14);
        RetryingCameraStateOpenerImpl r06 = this.this$0;
        String r23 = this.$cameraId;
        Camera2DeviceCloser r24 = this.$camera2DeviceCloser;
        this.label = 1;
        r04 = RetryingCameraStateOpener.m476openCameraWithRetryaeCOTgg$default(r06, r23, r24, null, this, 4, null);
        if (r04 != r7) goto L13;
        goto L13
    }
}
