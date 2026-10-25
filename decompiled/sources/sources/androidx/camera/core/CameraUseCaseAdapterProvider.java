package androidx.camera.core;

import androidx.camera.core.impl.AdapterCameraInfo;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.internal.CameraUseCaseAdapter;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J<\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Landroidx/camera/core/CameraUseCaseAdapterProvider;", _UrlKt.FRAGMENT_ENCODE_SET, "provide", "Landroidx/camera/core/internal/CameraUseCaseAdapter;", "cameraId", _UrlKt.FRAGMENT_ENCODE_SET, "camera", "Landroidx/camera/core/impl/CameraInternal;", "secondaryCamera", "adapterCameraInfo", "Landroidx/camera/core/impl/AdapterCameraInfo;", "secondaryAdapterCameraInfo", "compositionSettings", "Landroidx/camera/core/CompositionSettings;", "secondaryCompositionSettings", "camera-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraUseCaseAdapterProvider {
    CameraUseCaseAdapter provide(CameraInternal r1, CameraInternal r2, AdapterCameraInfo r3, AdapterCameraInfo r4, CompositionSettings r5, CompositionSettings r6);

    CameraUseCaseAdapter provide(String r1);
}
