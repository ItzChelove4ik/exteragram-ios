package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.core.DurationNs;
import kotlin.Metadata;
import okhttp3.internal.connection.RealConnection;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\"\u0014\u0010\u0001\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0001\u0010\u0002\"\u0014\u0010\u0003\u001a\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0002\"\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00000\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/pipe/core/DurationNs;", "defaultCameraRetryTimeoutNs", "J", "activeResumeCameraRetryTimeoutNs", _UrlKt.FRAGMENT_ENCODE_SET, "activeResumeCameraRetryThresholds", "[Landroidx/camera/camera2/pipe/core/DurationNs;", "camera-camera2-pipe"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class RetryingCameraStateOpenerKt {
    private static final DurationNs[] activeResumeCameraRetryThresholds = null;
    private static final long activeResumeCameraRetryTimeoutNs = 0;
    private static final long defaultCameraRetryTimeoutNs = 0;

    static {
        defaultCameraRetryTimeoutNs = DurationNs.m498constructorimpl(RealConnection.IDLE_CONNECTION_HEALTHY_NS);
        activeResumeCameraRetryTimeoutNs = DurationNs.m498constructorimpl(1800000000000L);
        activeResumeCameraRetryThresholds = new DurationNs[]{DurationNs.m496boximpl(DurationNs.m498constructorimpl(120000000000L)), DurationNs.m496boximpl(DurationNs.m498constructorimpl(300000000000L))};
    }

    public static final /* synthetic */ DurationNs[] access$getActiveResumeCameraRetryThresholds$p() {
        return activeResumeCameraRetryThresholds;
    }

    public static final /* synthetic */ long access$getActiveResumeCameraRetryTimeoutNs$p() {
        return activeResumeCameraRetryTimeoutNs;
    }

    public static final /* synthetic */ long access$getDefaultCameraRetryTimeoutNs$p() {
        return defaultCameraRetryTimeoutNs;
    }
}
