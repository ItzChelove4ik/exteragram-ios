package androidx.camera.camera2.config;

import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.internal.StreamSpecsCalculator;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001\u0004J\b\u0010\u0002\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0005À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/config/CameraComponent;", _UrlKt.FRAGMENT_ENCODE_SET, "getCameraInternal", "Landroidx/camera/core/impl/CameraInternal;", "Builder", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraComponent {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0004\u001a\u00020\u0005H'J\b\u0010\u0006\u001a\u00020\u0007H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/config/CameraComponent$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "config", "Landroidx/camera/camera2/config/CameraConfig;", "streamSpecsCalculator", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "build", "Landroidx/camera/camera2/config/CameraComponent;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Builder {
        CameraComponent build();

        Builder config(CameraConfig r1);

        Builder streamSpecsCalculator(StreamSpecsCalculator r1);
    }

    CameraInternal getCameraInternal();
}
