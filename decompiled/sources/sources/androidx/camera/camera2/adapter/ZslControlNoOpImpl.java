package androidx.camera.camera2.adapter;

import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.SessionConfig;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\t\b\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0011\u001a\u00020\nH\u0016J\u0010\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\nH\u0016J\b\u0010\u0013\u001a\u00020\nH\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016¨\u0006\u0016"}, d2 = {"Landroidx/camera/camera2/adapter/ZslControlNoOpImpl;", "Landroidx/camera/camera2/adapter/ZslControl;", "<init>", "()V", "addZslConfig", _UrlKt.FRAGMENT_ENCODE_SET, "sessionConfigBuilder", "Landroidx/camera/core/impl/SessionConfig$Builder;", "clearZslConfig", "isZslSurface", _UrlKt.FRAGMENT_ENCODE_SET, "surface", "Landroidx/camera/core/impl/DeferrableSurface;", "sessionConfig", "Landroidx/camera/core/impl/SessionConfig;", "setZslDisabledByUserCaseConfig", "disabled", "isZslDisabledByUserCaseConfig", "setZslDisabledByFlashMode", "isZslDisabledByFlashMode", "dequeueImageFromBuffer", "Landroidx/camera/core/ImageProxy;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ZslControlNoOpImpl implements ZslControl {
    public ZslControlNoOpImpl() {
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public void addZslConfig(SessionConfig.Builder r1) {
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public void clearZslConfig() {
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public ImageProxy dequeueImageFromBuffer() {
        return null;
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public boolean isZslDisabledByFlashMode() {
        return false;
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public boolean isZslDisabledByUserCaseConfig() {
        return false;
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public boolean isZslSurface(DeferrableSurface r1, SessionConfig r2) {
        return false;
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public void setZslDisabledByFlashMode(boolean r1) {
    }

    @Override // androidx.camera.camera2.adapter.ZslControl
    public void setZslDisabledByUserCaseConfig(boolean r1) {
    }
}
