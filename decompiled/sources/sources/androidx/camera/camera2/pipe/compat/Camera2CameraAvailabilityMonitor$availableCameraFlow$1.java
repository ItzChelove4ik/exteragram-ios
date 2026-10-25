package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraManager;
import android.os.Build;
import androidx.camera.camera2.pipe.CameraId;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/channels/ProducerScope;", "Landroidx/camera/camera2/pipe/CameraId;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2CameraAvailabilityMonitor$availableCameraFlow$1", f = "RetryingCameraStateOpener.kt", i = {}, l = {158}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class Camera2CameraAvailabilityMonitor$availableCameraFlow$1 extends SuspendLambda implements Function2<ProducerScope<? super CameraId>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ Camera2CameraAvailabilityMonitor this$0;

    public static Unit $r8$lambda$Ha8FIlMaNkz7up8iOeYm55Nl5eE(CameraManager r0, Camera2CameraAvailabilityMonitor$availableCameraFlow$1$availabilityCallback$1 r1) {
        r0.unregisterAvailabilityCallback(r1);
        return Unit.INSTANCE;
    }

    public Camera2CameraAvailabilityMonitor$availableCameraFlow$1(Camera2CameraAvailabilityMonitor r1, Continuation<? super Camera2CameraAvailabilityMonitor$availableCameraFlow$1> r2) {
        this.this$0 = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        Camera2CameraAvailabilityMonitor$availableCameraFlow$1 r0 = new Camera2CameraAvailabilityMonitor$availableCameraFlow$1(this.this$0, r3);
        r0.L$0 = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super CameraId> r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(ProducerScope<? super CameraId> r1, Continuation<? super Unit> r2) {
        return ((Camera2CameraAvailabilityMonitor$availableCameraFlow$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L8;
        if (r1 != 1) goto L6;
        ResultKt.throwOnFailure(r8);
    L16:
        return Unit.INSTANCE;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L8:
        ResultKt.throwOnFailure(r8);
        ProducerScope r82 = (ProducerScope) this.L$0;
        Camera2CameraAvailabilityMonitor$availableCameraFlow$1$availabilityCallback$1 r12 = new Camera2CameraAvailabilityMonitor$availableCameraFlow$1$availabilityCallback$1(r82);
        CameraManager r3 = (CameraManager) Camera2CameraAvailabilityMonitor.access$getCameraManager$p(this.this$0).get();
        int r4 = Build.VERSION.SDK_INT;
        Camera2CameraAvailabilityMonitor r5 = this.this$0;
        if (r4 < 28) goto L11;
        Api28Compat.registerAvailabilityCallback(r3, Camera2CameraAvailabilityMonitor.access$getThreads$p(r5).getCamera2Executor(), r12);
    L12:
        Camera2CameraAvailabilityMonitor$availableCameraFlow$1$$ExternalSyntheticLambda0 r42 = new Camera2CameraAvailabilityMonitor$availableCameraFlow$1$$ExternalSyntheticLambda0(r3, r12);
        this.label = 1;
        if (ProduceKt.awaitClose(r82, r42, this) != r0) goto L16;
        return r0;
    L11:
        r3.registerAvailabilityCallback(r12, Camera2CameraAvailabilityMonitor.access$getThreads$p(r5).getCamera2Handler());
        goto L12
    }
}
