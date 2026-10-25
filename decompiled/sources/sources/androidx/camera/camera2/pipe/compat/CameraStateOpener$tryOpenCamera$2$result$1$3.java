package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.core.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/compat/OpenCameraResult;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CameraStateOpener$tryOpenCamera$2$result$1$3", f = "RetryingCameraStateOpener.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nRetryingCameraStateOpener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/CameraStateOpener$tryOpenCamera$2$result$1$3\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,665:1\n50#2,2:666\n82#2,2:668\n*S KotlinDebug\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/CameraStateOpener$tryOpenCamera$2$result$1$3\n*L\n338#1:666,2\n341#1:668,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraStateOpener$tryOpenCamera$2$result$1$3 extends SuspendLambda implements Function1<Continuation<? super OpenCameraResult>, Object> {
    final /* synthetic */ Ref.ObjectRef<Deferred<OpenCameraResult>> $cameraOpenDeferred;
    final /* synthetic */ AndroidCameraState $cameraState;
    final /* synthetic */ Ref.ObjectRef<Job> $timeoutJob;
    int label;

    public CameraStateOpener$tryOpenCamera$2$result$1$3(Ref.ObjectRef<Job> r1, Ref.ObjectRef<Deferred<OpenCameraResult>> r2, AndroidCameraState r3, Continuation<? super CameraStateOpener$tryOpenCamera$2$result$1$3> r4) {
        this.$timeoutJob = r1;
        this.$cameraOpenDeferred = r2;
        this.$cameraState = r3;
        super(1, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r4) {
        return new CameraStateOpener$tryOpenCamera$2$result$1$3(this.$timeoutJob, this.$cameraOpenDeferred, this.$cameraState, r4);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super OpenCameraResult> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super OpenCameraResult> r1) {
        return ((CameraStateOpener$tryOpenCamera$2$result$1$3) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r4) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L15;
        ResultKt.throwOnFailure(r4);
        Log r42 = Log.INSTANCE;
        if (r42.getDEBUG_LOGGABLE() == false) goto L7;
        android.util.Log.d("CXCP", "tryOpenCamera: 3000ms elapsed");
    L7:
        this.$timeoutJob.element = null;
        if (this.$cameraOpenDeferred.element != null) goto L10;
        return null;
    L10:
        if (r42.getERROR_LOGGABLE() == false) goto L12;
        android.util.Log.e("CXCP", "tryOpenCamera: openCamera() timed out");
    L12:
        this.$cameraState.close();
        return new OpenCameraResult(null, CameraError.m175boximpl(CameraError.Companion.m192getERROR_CAMERA_OPEN_TIMEOUTv7Vf74A()), 1, null);
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
