package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraError;
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
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/compat/OpenCameraResult;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1", f = "RetryingCameraStateOpener.kt", i = {}, l = {280}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nRetryingCameraStateOpener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,665:1\n75#2,2:666\n*S KotlinDebug\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1\n*L\n282#1:666,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super OpenCameraResult>, Object> {
    final /* synthetic */ String $cameraId;
    final /* synthetic */ AndroidCameraState $cameraState;
    int label;
    final /* synthetic */ CameraStateOpener this$0;

    public CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1(CameraStateOpener r1, String r2, AndroidCameraState r3, Continuation<? super CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1> r4) {
        this.this$0 = r1;
        this.$cameraId = r2;
        this.$cameraState = r3;
        super(2, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        return new CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1(this.this$0, this.$cameraId, this.$cameraState, r4);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super OpenCameraResult> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super OpenCameraResult> r2) {
        return ((CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v7 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L11;
        if (r1 != 1) goto L9;
        ResultKt.throwOnFailure(r6);     // Catch: Exception -> L7
        CameraStateOpener$tryOpenCamera$2$cameraOpenDeferred$1 this = this;
    L20:
    L19:
        return null;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L11:
        ResultKt.throwOnFailure(r6);
        CameraOpener r62 = CameraStateOpener.access$getCameraOpener$p(this.this$0);     // Catch: Exception -> L7
        String r12 = this.$cameraId;     // Catch: Exception -> L7
        AndroidCameraState r4 = this.$cameraState;     // Catch: Exception -> L7
        this.label = 1;     // Catch: Exception -> L7
        Object r5 = r62.mo436openCameraRzXb1QE(r12, r4, this);     // Catch: Exception -> L7
        this = r5;
        if (r5 != r0) goto L20;
        return r0;
    L7:
        e = move-exception;
        Log r02 = Log.INSTANCE;
        String r13 = this.$cameraId;
        if (r02.getWARN_LOGGABLE() == false) goto L18;
        android.util.Log.w("CXCP", "Failed to open " + CameraId.m232toStringimpl(r13), e);
    L18:
        this.$cameraState.closeWith$camera_camera2_pipe(e);
        new OpenCameraResult(null, CameraError.m175boximpl(CameraError.Companion.m185fromPVuDhNw$camera_camera2_pipe(e)), 1, null);
        goto L19
    }
}
