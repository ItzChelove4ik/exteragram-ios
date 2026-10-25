package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraManager;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.internal.CameraStatusMonitor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.ChannelsKt;
import kotlinx.coroutines.channels.ProducerScope;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\b"}, d2 = {"androidx/camera/camera2/pipe/compat/Camera2CameraStatusMonitor$cameraStatusFlow$1$availabilityCallback$1", "Landroid/hardware/camera2/CameraManager$AvailabilityCallback;", "onCameraAccessPrioritiesChanged", _UrlKt.FRAGMENT_ENCODE_SET, "onCameraAvailable", "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "onCameraUnavailable", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2CameraStatusMonitor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2CameraStatusMonitor.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraStatusMonitor$cameraStatusFlow$1$availabilityCallback$1\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 Channel.kt\nkotlinx/coroutines/channels/ChannelKt\n+ 4 CameraDevices.kt\nandroidx/camera/camera2/pipe/CameraId$Companion\n*L\n1#1,124:1\n50#2,2:125\n71#2,2:128\n50#2,2:131\n71#2,2:135\n50#2,2:138\n71#2,2:142\n544#3:127\n545#3:130\n544#3:134\n545#3:137\n544#3:141\n545#3:144\n172#4:133\n172#4:140\n*S KotlinDebug\n*F\n+ 1 Camera2CameraStatusMonitor.kt\nandroidx/camera/camera2/pipe/compat/Camera2CameraStatusMonitor$cameraStatusFlow$1$availabilityCallback$1\n*L\n82#1:125,2\n84#1:128,2\n90#1:131,2\n92#1:135,2\n97#1:138,2\n101#1:142,2\n83#1:127\n83#1:130\n92#1:134\n92#1:137\n101#1:141\n101#1:144\n91#1:133\n99#1:140\n*E\n"})
/* loaded from: classes3.dex */
public final class Camera2CameraStatusMonitor$cameraStatusFlow$1$availabilityCallback$1 extends CameraManager.AvailabilityCallback {
    final /* synthetic */ ProducerScope<CameraStatusMonitor.CameraStatus> $$this$callbackFlow;
    final /* synthetic */ Camera2CameraStatusMonitor this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public Camera2CameraStatusMonitor$cameraStatusFlow$1$availabilityCallback$1(ProducerScope<? super CameraStatusMonitor.CameraStatus> r1, Camera2CameraStatusMonitor r2) {
        this.$$this$callbackFlow = r1;
        this.this$0 = r2;
    }

    @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
    public void onCameraAccessPrioritiesChanged() {
        Log r0 = Log.INSTANCE;
        if (r0.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", "Camera access priorities have changed");
    L5:
        Object r3 = ChannelsKt.trySendBlocking(this.$$this$callbackFlow, CameraStatusMonitor.CameraStatus.CameraPrioritiesChanged.INSTANCE);
        if ((r3 instanceof ChannelResult.Failed) == false) goto L11;
        ChannelResult.m3537exceptionOrNullimpl(r3);
        if (r0.getWARN_LOGGABLE() == false) goto L12;
        android.util.Log.w("CXCP", "Failed to emit CameraPrioritiesChanged");
        return;
    L12:
        return;
    }

    @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
    public void onCameraAvailable(String r6) {
        if (Intrinsics.areEqual(r6, Camera2CameraStatusMonitor.access$getCameraId$p(this.this$0)) == false) goto L16;
        Log r0 = Log.INSTANCE;
        if (r0.getDEBUG_LOGGABLE() == false) goto L8;
        android.util.Log.d("CXCP", "Camera " + r6 + " has become available");
    L8:
        Object r5 = ChannelsKt.trySendBlocking(this.$$this$callbackFlow, new CameraStatusMonitor.CameraStatus.CameraAvailable(CameraId.m228constructorimpl(r6), null));
        if ((r5 instanceof ChannelResult.Failed) == false) goto L14;
        ChannelResult.m3537exceptionOrNullimpl(r5);
        if (r0.getWARN_LOGGABLE() == false) goto L15;
        android.util.Log.w("CXCP", "Failed to emit CameraAvailable(" + r6 + ')');
        return;
    L15:
        return;
    L14:
        return;
    }

    @Override // android.hardware.camera2.CameraManager.AvailabilityCallback
    public void onCameraUnavailable(String r6) {
        if (Intrinsics.areEqual(r6, Camera2CameraStatusMonitor.access$getCameraId$p(this.this$0)) == false) goto L16;
        Log r0 = Log.INSTANCE;
        if (r0.getDEBUG_LOGGABLE() == false) goto L8;
        android.util.Log.d("CXCP", "Camera " + r6 + " has become unavailable");
    L8:
        Object r5 = ChannelsKt.trySendBlocking(this.$$this$callbackFlow, new CameraStatusMonitor.CameraStatus.CameraUnavailable(CameraId.m228constructorimpl(r6), null));
        if ((r5 instanceof ChannelResult.Failed) == false) goto L14;
        ChannelResult.m3537exceptionOrNullimpl(r5);
        if (r0.getWARN_LOGGABLE() == false) goto L15;
        android.util.Log.w("CXCP", "Failed to emit CameraUnavailable(" + r6 + ')');
        return;
    L15:
        return;
    L14:
        return;
    }
}
