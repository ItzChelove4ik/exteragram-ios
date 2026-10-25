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
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/compat/OpenCameraResult;", "it"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CameraStateOpener$tryOpenCamera$2$result$1$1", f = "RetryingCameraStateOpener.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nRetryingCameraStateOpener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/CameraStateOpener$tryOpenCamera$2$result$1$1\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,665:1\n50#2,2:666\n*S KotlinDebug\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/CameraStateOpener$tryOpenCamera$2$result$1$1\n*L\n326#1:666,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraStateOpener$tryOpenCamera$2$result$1$1 extends SuspendLambda implements Function2<OpenCameraResult, Continuation<? super OpenCameraResult>, Object> {
    final /* synthetic */ String $cameraId;
    final /* synthetic */ Ref.ObjectRef<Deferred<OpenCameraResult>> $cameraOpenDeferred;
    /* synthetic */ Object L$0;
    int label;

    public CameraStateOpener$tryOpenCamera$2$result$1$1(Ref.ObjectRef<Deferred<OpenCameraResult>> r1, String r2, Continuation<? super CameraStateOpener$tryOpenCamera$2$result$1$1> r3) {
        this.$cameraOpenDeferred = r1;
        this.$cameraId = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
        CameraStateOpener$tryOpenCamera$2$result$1$1 r0 = new CameraStateOpener$tryOpenCamera$2$result$1$1(this.$cameraOpenDeferred, this.$cameraId, r4);
        r0.L$0 = r3;
        return r0;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(OpenCameraResult r1, Continuation<? super OpenCameraResult> r2) {
        return ((CameraStateOpener$tryOpenCamera$2$result$1$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(OpenCameraResult r1, Continuation<? super OpenCameraResult> r2) {
        return invoke2(r1, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r5) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L9;
        ResultKt.throwOnFailure(r5);
        OpenCameraResult r52 = (OpenCameraResult) this.L$0;
        Log r0 = Log.INSTANCE;
        String r2 = this.$cameraId;
        if (r0.getDEBUG_LOGGABLE() == false) goto L7;
        android.util.Log.d("CXCP", "tryOpenCamera: openCamera() for " + CameraId.m232toStringimpl(r2) + " returned");
    L7:
        this.$cameraOpenDeferred.element = null;
        return r52;
    L9:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
