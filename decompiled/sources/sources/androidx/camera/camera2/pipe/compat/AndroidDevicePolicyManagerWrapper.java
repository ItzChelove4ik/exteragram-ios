package androidx.camera.camera2.pipe.compat;

import android.app.admin.DevicePolicyManager;
import android.os.Trace;
import androidx.camera.camera2.pipe.core.Debug;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/pipe/compat/AndroidDevicePolicyManagerWrapper;", "Landroidx/camera/camera2/pipe/compat/DevicePolicyManagerWrapper;", "devicePolicyManager", "Landroid/app/admin/DevicePolicyManager;", "<init>", "(Landroid/app/admin/DevicePolicyManager;)V", "camerasDisabled", _UrlKt.FRAGMENT_ENCODE_SET, "getCamerasDisabled", "()Z", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRetryingCameraStateOpener.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/AndroidDevicePolicyManagerWrapper\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n1#1,665:1\n48#2,2:666\n71#2,4:668\n50#2,3:672\n78#2,4:675\n*S KotlinDebug\n*F\n+ 1 RetryingCameraStateOpener.kt\nandroidx/camera/camera2/pipe/compat/AndroidDevicePolicyManagerWrapper\n*L\n201#1:666,2\n201#1:668,4\n201#1:672,3\n201#1:675,4\n*E\n"})
/* loaded from: classes3.dex */
public final class AndroidDevicePolicyManagerWrapper implements DevicePolicyManagerWrapper {
    private final DevicePolicyManager devicePolicyManager;

    public AndroidDevicePolicyManagerWrapper(DevicePolicyManager r1) {
        this.devicePolicyManager = r1;
    }

    public static final /* synthetic */ DevicePolicyManager access$getDevicePolicyManager$p(AndroidDevicePolicyManagerWrapper r0) {
        return r0.devicePolicyManager;
    }

    @Override // androidx.camera.camera2.pipe.compat.DevicePolicyManagerWrapper
    public boolean getCamerasDisabled() {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection("DevicePolicyManager#getCameraDisabled");     // Catch: Throwable -> L6
        boolean r1 = access$getDevicePolicyManager$p(this).getCameraDisabled(null);     // Catch: Throwable -> L6
        Trace.endSection();
        return r1;
    L6:
        th = move-exception;
        Trace.endSection();
        throw th;
    }
}
