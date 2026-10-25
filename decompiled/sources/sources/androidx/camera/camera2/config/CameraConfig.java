package androidx.camera.camera2.config;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\t\u001a\u00020\u0000H\u0007R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Landroidx/camera/camera2/config/CameraConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCameraId-Dz_R5H8", "()Ljava/lang/String;", "Ljava/lang/String;", "provideCameraConfig", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraConfig {
    private final String cameraId;

    private CameraConfig(String r1) {
        this.cameraId = r1;
    }

    public /* synthetic */ CameraConfig(String r1, DefaultConstructorMarker r2) {
        this(r1);
    }

    /* renamed from: getCameraId-Dz_R5H8, reason: not valid java name */
    public final String m37getCameraIdDz_R5H8() {
        return this.cameraId;
    }

    public final CameraConfig provideCameraConfig() {
        return this;
    }
}
