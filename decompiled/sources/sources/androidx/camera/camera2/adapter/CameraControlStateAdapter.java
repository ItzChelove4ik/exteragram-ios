package androidx.camera.camera2.adapter;

import android.annotation.SuppressLint;
import androidx.camera.camera2.impl.EvCompControl;
import androidx.camera.camera2.impl.LowLightBoostControl;
import androidx.camera.camera2.impl.TorchControl;
import androidx.camera.camera2.impl.ZoomControl;
import androidx.camera.core.ZoomState;
import androidx.lifecycle.LiveData;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\fR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\rR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010\u000eR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u000fR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/adapter/CameraControlStateAdapter;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/impl/ZoomControl;", "zoomControl", "Landroidx/camera/camera2/impl/EvCompControl;", "evCompControl", "Landroidx/camera/camera2/impl/TorchControl;", "torchControl", "Landroidx/camera/camera2/impl/LowLightBoostControl;", "lowLightBoostControl", "<init>", "(Landroidx/camera/camera2/impl/ZoomControl;Landroidx/camera/camera2/impl/EvCompControl;Landroidx/camera/camera2/impl/TorchControl;Landroidx/camera/camera2/impl/LowLightBoostControl;)V", "Landroidx/camera/camera2/impl/ZoomControl;", "Landroidx/camera/camera2/impl/EvCompControl;", "Landroidx/camera/camera2/impl/TorchControl;", "Landroidx/camera/camera2/impl/LowLightBoostControl;", "Landroidx/lifecycle/LiveData;", "Landroidx/camera/core/ZoomState;", "getZoomStateLiveData", "()Landroidx/lifecycle/LiveData;", "zoomStateLiveData", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"UnsafeOptInUsageError"})
/* loaded from: classes3.dex */
public final class CameraControlStateAdapter {
    private final EvCompControl evCompControl;
    private final LowLightBoostControl lowLightBoostControl;
    private final TorchControl torchControl;
    private final ZoomControl zoomControl;

    public CameraControlStateAdapter(ZoomControl r1, EvCompControl r2, TorchControl r3, LowLightBoostControl r4) {
        this.zoomControl = r1;
        this.evCompControl = r2;
        this.torchControl = r3;
        this.lowLightBoostControl = r4;
    }

    public final LiveData<ZoomState> getZoomStateLiveData() {
        return this.zoomControl.getZoomStateLiveData();
    }
}
