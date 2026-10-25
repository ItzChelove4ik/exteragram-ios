package androidx.camera.camera2.pipe.compat;

import android.hardware.camera2.CameraDevice;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H¦@¢\u0006\u0004\b\b\u0010\tø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CameraOpener;", _UrlKt.FRAGMENT_ENCODE_SET, "openCamera", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "stateCallback", "Landroid/hardware/camera2/CameraDevice$StateCallback;", "openCamera-RzXb1QE", "(Ljava/lang/String;Landroid/hardware/camera2/CameraDevice$StateCallback;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraOpener {
    /* renamed from: openCamera-RzXb1QE */
    Object mo436openCameraRzXb1QE(String r1, CameraDevice.StateCallback r2, Continuation<? super Unit> r3);
}
