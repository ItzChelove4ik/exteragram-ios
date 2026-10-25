package androidx.camera.camera2.adapter;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.SessionConfig;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\bH&J\b\u0010\u000f\u001a\u00020\bH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\bH&J\b\u0010\u0011\u001a\u00020\bH&J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/adapter/ZslControl;", _UrlKt.FRAGMENT_ENCODE_SET, "addZslConfig", _UrlKt.FRAGMENT_ENCODE_SET, "sessionConfigBuilder", "Landroidx/camera/core/impl/SessionConfig$Builder;", "clearZslConfig", "isZslSurface", _UrlKt.FRAGMENT_ENCODE_SET, "surface", "Landroidx/camera/core/impl/DeferrableSurface;", "sessionConfig", "Landroidx/camera/core/impl/SessionConfig;", "setZslDisabledByUserCaseConfig", "disabled", "isZslDisabledByUserCaseConfig", "setZslDisabledByFlashMode", "isZslDisabledByFlashMode", "dequeueImageFromBuffer", "Landroidx/camera/core/ImageProxy;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ZslControl {
    void addZslConfig(SessionConfig.Builder r1);

    void clearZslConfig();

    ImageProxy dequeueImageFromBuffer();

    boolean isZslDisabledByFlashMode();

    boolean isZslDisabledByUserCaseConfig();

    boolean isZslSurface(DeferrableSurface r1, SessionConfig r2);

    void setZslDisabledByFlashMode(boolean r1);

    void setZslDisabledByUserCaseConfig(boolean r1);
}
