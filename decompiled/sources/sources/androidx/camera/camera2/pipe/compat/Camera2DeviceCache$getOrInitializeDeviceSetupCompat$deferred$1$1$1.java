package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraAccessException;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import androidx.camera.featurecombinationquery.CameraDeviceSetupCompat;
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

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "Landroidx/camera/featurecombinationquery/CameraDeviceSetupCompat;", "<anonymous>", "(Lkotlinx/coroutines/CoroutineScope;)Landroidx/camera/featurecombinationquery/CameraDeviceSetupCompat;"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2DeviceCache$getOrInitializeDeviceSetupCompat$deferred$1$1$1", f = "Camera2DeviceCache.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nCamera2DeviceCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2DeviceCache.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCache$getOrInitializeDeviceSetupCompat$deferred$1$1$1\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 Exceptions.kt\nandroidx/camera/camera2/pipe/compat/ExceptionsKt\n*L\n1#1,391:1\n50#2,2:392\n71#2,2:400\n50#2,2:426\n53#3,6:394\n59#3,24:402\n83#3,3:428\n*S KotlinDebug\n*F\n+ 1 Camera2DeviceCache.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCache$getOrInitializeDeviceSetupCompat$deferred$1$1$1\n*L\n120#1:392,2\n121#1:400,2\n121#1:426,2\n121#1:394,6\n121#1:402,24\n121#1:428,3\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2DeviceCache$getOrInitializeDeviceSetupCompat$deferred$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CameraDeviceSetupCompat>, Object> {
    final /* synthetic */ String $cameraId;
    int label;
    final /* synthetic */ Camera2DeviceCache this$0;

    public Camera2DeviceCache$getOrInitializeDeviceSetupCompat$deferred$1$1$1(String r1, Camera2DeviceCache r2, Continuation<? super Camera2DeviceCache$getOrInitializeDeviceSetupCompat$deferred$1$1$1> r3) {
        this.$cameraId = r1;
        this.this$0 = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new Camera2DeviceCache$getOrInitializeDeviceSetupCompat$deferred$1$1$1(this.$cameraId, this.this$0, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super CameraDeviceSetupCompat> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super CameraDeviceSetupCompat> r2) {
        return ((Camera2DeviceCache$getOrInitializeDeviceSetupCompat$deferred$1$1$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L37;
        ResultKt.throwOnFailure(r6);
        Log r62 = Log.INSTANCE;
        String r0 = this.$cameraId;
        if (r62.getDEBUG_LOGGABLE() == false) goto L7;
        android.util.Log.d("CXCP", "Initializing CameraDeviceSetupCompat for " + CameraId.m232toStringimpl(r0));
    L7:
        String r63 = this.$cameraId;
        CameraErrorListener r02 = Camera2DeviceCache.access$getCameraErrorListener$p(this.this$0);
        Camera2DeviceCache r3 = this.this$0;
        return Camera2DeviceCache.access$getCameraDeviceSetupCompatFactory(r3).getCameraDeviceSetupCompat(this.$cameraId);
    L10:
        e = move-exception;
        if ((e instanceof CameraAccessException) == false) goto L18;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L16;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());
    L16:
        r02.mo447onCameraError3M5Xam4(r63, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);
    L36:
        return null;
    L18:
        if ((e instanceof IllegalArgumentException) == true) goto L33;
        if ((e instanceof SecurityException) == true) goto L33;
        if ((e instanceof UnsupportedOperationException) == true) goto L33;
        if ((e instanceof NullPointerException) == true) goto L33;
        if ((e instanceof IllegalStateException) == true) goto L29;
        throw e;
    L29:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L36;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");
    L33:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L35;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());
    L35:
        r02.mo447onCameraError3M5Xam4(r63, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);
        goto L36
    L37:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
