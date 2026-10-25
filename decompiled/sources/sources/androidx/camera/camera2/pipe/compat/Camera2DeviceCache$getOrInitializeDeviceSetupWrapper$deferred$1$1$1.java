package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.internal.CameraErrorListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceSetup;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$deferred$1$1$1", f = "Camera2DeviceCache.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nCamera2DeviceCache.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2DeviceCache.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$deferred$1$1$1\n+ 2 Exceptions.kt\nandroidx/camera/camera2/pipe/compat/ExceptionsKt\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,391:1\n53#2,6:392\n59#2,24:400\n83#2,3:426\n53#2,6:431\n59#2,24:439\n83#2,3:465\n71#3,2:398\n50#3,2:424\n50#3,2:429\n71#3,2:437\n50#3,2:463\n*S KotlinDebug\n*F\n+ 1 Camera2DeviceCache.kt\nandroidx/camera/camera2/pipe/compat/Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$deferred$1$1$1\n*L\n152#1:392,6\n152#1:400,24\n152#1:426,3\n160#1:431,6\n160#1:439,24\n160#1:465,3\n152#1:398,2\n152#1:424,2\n159#1:429,2\n160#1:437,2\n160#1:463,2\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$deferred$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Camera2DeviceSetup>, Object> {
    final /* synthetic */ String $cameraId;
    int label;
    final /* synthetic */ Camera2DeviceCache this$0;

    public Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$deferred$1$1$1(String r1, Camera2DeviceCache r2, Continuation<? super Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$deferred$1$1$1> r3) {
        this.$cameraId = r1;
        this.this$0 = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$deferred$1$1$1(this.$cameraId, this.this$0, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Camera2DeviceSetup> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Camera2DeviceSetup> r2) {
        return ((Camera2DeviceCache$getOrInitializeDeviceSetupWrapper$deferred$1$1$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L73;
        ResultKt.throwOnFailure(r12);
        String r122 = this.$cameraId;
        CameraErrorListener r4 = Camera2DeviceCache.access$getCameraErrorListener$p(this.this$0);
        Camera2DeviceCache r6 = this.this$0;
        Boolean r123 = Boxing.boxBoolean(((CameraManager) Camera2DeviceCache.access$getCameraManager$p(r6).get()).isCameraDeviceSetupSupported(this.$cameraId));     // Catch: Exception -> L7
    L35:
        if (Intrinsics.areEqual(r123, Boxing.boxBoolean(true)) == true) goto L37;
        return null;
    L37:
        Log r124 = Log.INSTANCE;
        String r42 = this.$cameraId;
        if (r124.getDEBUG_LOGGABLE() == false) goto L40;
        android.util.Log.d("CXCP", "Initializing CameraDeviceSetup for " + CameraId.m232toStringimpl(r42));
    L40:
        String r125 = this.$cameraId;
        CameraErrorListener r43 = Camera2DeviceCache.access$getCameraErrorListener$p(this.this$0);
        Camera2DeviceCache r62 = this.this$0;
        CameraDevice.CameraDeviceSetup r126 = ((CameraManager) Camera2DeviceCache.access$getCameraManager$p(r62).get()).getCameraDeviceSetup(this.$cameraId);     // Catch: Exception -> L43
    L70:
        if (r126 != null) goto L72;
        return null;
    L72:
        return new Camera2DeviceSetup(r126, this.$cameraId, Camera2DeviceCache.access$getCameraErrorListener$p(this.this$0), null);
    L43:
        e = move-exception;
        if ((e instanceof CameraAccessException) == false) goto L52;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L49;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());
    L49:
        r43.mo447onCameraError3M5Xam4(r125, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);
    L50:
        r126 = null;
        goto L70
    L52:
        if ((e instanceof IllegalArgumentException) == true) goto L67;
        if ((e instanceof SecurityException) == true) goto L67;
        if ((e instanceof UnsupportedOperationException) == true) goto L67;
        if ((e instanceof NullPointerException) == true) goto L67;
        if ((e instanceof IllegalStateException) == true) goto L63;
        throw e;
    L63:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L50;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");
    L67:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L69;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());
    L69:
        r43.mo447onCameraError3M5Xam4(r125, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);
    L7:
        e = move-exception;
        if ((e instanceof CameraAccessException) == false) goto L16;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L13;
        android.util.Log.w("CXCP", "Failed to execute call: Camera encountered an error: " + e.getMessage());
    L13:
        r4.mo447onCameraError3M5Xam4(r122, CameraError.Companion.m184fromPVuDhNw$camera_camera2_pipe((CameraAccessException) e), true);
    L14:
        r123 = null;
        goto L35
    L16:
        if ((e instanceof IllegalArgumentException) == true) goto L31;
        if ((e instanceof SecurityException) == true) goto L31;
        if ((e instanceof UnsupportedOperationException) == true) goto L31;
        if ((e instanceof NullPointerException) == true) goto L31;
        if ((e instanceof IllegalStateException) == true) goto L27;
        throw e;
    L27:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L14;
        android.util.Log.d("CXCP", "Failed to execute call: Camera may be closed");
    L31:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L33;
        android.util.Log.w("CXCP", "Failed to execute call: Unexpected exception: " + e.getMessage());
    L33:
        r4.mo447onCameraError3M5Xam4(r122, CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false);
        goto L14
    L73:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
