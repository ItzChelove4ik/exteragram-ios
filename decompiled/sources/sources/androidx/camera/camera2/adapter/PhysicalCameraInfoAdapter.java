package androidx.camera.camera2.adapter;

import android.annotation.SuppressLint;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraMetadata;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.interop.Camera2CameraInfo;
import androidx.camera.camera2.pipe.UnsafeWrapper;
import androidx.camera.core.CameraIdentifier;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraState;
import androidx.camera.core.ZoomState;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.lifecycle.LiveData;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.CharsKt__CharKt$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002¢\u0006\u0004\b\t\u0010\nJ\u000f\u0010\u000b\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000b\u0010\nJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0015\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u0011H\u0016¢\u0006\u0004\b\u0016\u0010\u0014J\u000f\u0010\u0017\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u0017\u0010\fJ\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00010\u001bH\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 J)\u0010%\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\"*\u00020!2\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0017¢\u0006\u0004\b%\u0010&R\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010'R!\u0010/\u001a\u00020(8@X\u0081\u0084\u0002¢\u0006\u0012\n\u0004\b)\u0010*\u0012\u0004\b-\u0010.\u001a\u0004\b+\u0010,¨\u00060"}, d2 = {"Landroidx/camera/camera2/adapter/PhysicalCameraInfoAdapter;", "Landroidx/camera/core/CameraInfo;", "Landroidx/camera/camera2/pipe/UnsafeWrapper;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;)V", _UrlKt.FRAGMENT_ENCODE_SET, "lensFacingInt", "getCameraSelectorLensFacing", "(I)I", "getSensorRotationDegrees", "()I", "relativeRotation", _UrlKt.FRAGMENT_ENCODE_SET, "hasFlashUnit", "()Z", "Landroidx/lifecycle/LiveData;", "Landroidx/camera/core/ZoomState;", "getZoomState", "()Landroidx/lifecycle/LiveData;", "Landroidx/camera/core/CameraState;", "getCameraState", "getLensFacing", _UrlKt.FRAGMENT_ENCODE_SET, "getIntrinsicZoomRatio", "()F", _UrlKt.FRAGMENT_ENCODE_SET, "getPhysicalCameraInfos", "()Ljava/util/Set;", "Landroidx/camera/core/CameraIdentifier;", "getCameraIdentifier", "()Landroidx/camera/core/CameraIdentifier;", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Lkotlin/reflect/KClass;", "type", "unwrapAs", "(Lkotlin/reflect/KClass;)Ljava/lang/Object;", "Landroidx/camera/camera2/impl/CameraProperties;", "Landroidx/camera/camera2/interop/Camera2CameraInfo;", "camera2CameraInfo$delegate", "Lkotlin/Lazy;", "getCamera2CameraInfo$camera_camera2", "()Landroidx/camera/camera2/interop/Camera2CameraInfo;", "getCamera2CameraInfo$camera_camera2$annotations", "()V", "camera2CameraInfo", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"UnsafeOptInUsageError"})
/* loaded from: classes3.dex */
public final class PhysicalCameraInfoAdapter implements CameraInfo, UnsafeWrapper {
    private final Lazy camera2CameraInfo$delegate;
    private final CameraProperties cameraProperties;

    public static Camera2CameraInfo $r8$lambda$3ILLdl5fLYeNNpKoQbn0VSgRxXw(PhysicalCameraInfoAdapter r1) {
        return Camera2CameraInfo.Companion.create(r1.cameraProperties);
    }

    public PhysicalCameraInfoAdapter(CameraProperties r1) {
        this.cameraProperties = r1;
        this.camera2CameraInfo$delegate = LazyKt.lazy(new PhysicalCameraInfoAdapter$$ExternalSyntheticLambda0(this));
    }

    private final int getCameraSelectorLensFacing(int r2) {
        if (r2 != 0) goto L4;
        return 0;
    L4:
        if (r2 != 1) goto L6;
        return 1;
    L6:
        if (r2 != 2) goto L8;
        return 2;
    L8:
        CharsKt__CharKt$$ExternalSyntheticBUOutline0.m("The specified lens facing integer ", r2, " can not be recognized.");
        return 0;
    }

    public final Camera2CameraInfo getCamera2CameraInfo$camera_camera2() {
        return (Camera2CameraInfo) this.camera2CameraInfo$delegate.getValue();
    }

    @Override // androidx.camera.core.CameraInfo
    public CameraIdentifier getCameraIdentifier() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    @Override // androidx.camera.core.CameraInfo
    public LiveData<CameraState> getCameraState() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    @Override // androidx.camera.core.CameraInfo
    public float getIntrinsicZoomRatio() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    @Override // androidx.camera.core.CameraInfo
    public int getLensFacing() {
        return getCameraSelectorLensFacing(((Number) this.cameraProperties.getMetadata().get(CameraCharacteristics.LENS_FACING)).intValue());
    }

    @Override // androidx.camera.core.CameraInfo
    public Set<CameraInfo> getPhysicalCameraInfos() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    @Override // androidx.camera.core.CameraInfo
    public int getSensorRotationDegrees() {
        return getSensorRotationDegrees(0);
    }

    @Override // androidx.camera.core.CameraInfo
    public int getSensorRotationDegrees(int r3) {
        int r0 = ((Number) this.cameraProperties.getMetadata().get(CameraCharacteristics.SENSOR_ORIENTATION)).intValue();
        int r32 = CameraOrientationUtil.surfaceRotationToDegrees(r3);
        boolean r1 = true;
        if (1 == getLensFacing()) goto L7;
        r1 = false;
    L7:
        return CameraOrientationUtil.getRelativeImageRotation(r32, r0, r1);
    }

    @Override // androidx.camera.core.CameraInfo
    public LiveData<ZoomState> getZoomState() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    @Override // androidx.camera.core.CameraInfo
    public boolean hasFlashUnit() {
        throw new UnsupportedOperationException("Physical camera doesn't support this function");
    }

    @Override // androidx.camera.camera2.pipe.UnsafeWrapper
    public <T> T unwrapAs(KClass<T> r2) {
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(Camera2CameraInfo.class)) == false) goto L7;
        return (T) getCamera2CameraInfo$camera_camera2();
    L7:
        if (Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(CameraProperties.class)) == true) goto L9;
        boolean r0 = Intrinsics.areEqual(r2, Reflection.getOrCreateKotlinClass(CameraMetadata.class));
        CameraProperties r1 = this.cameraProperties;
        if (r0 == false) goto L15;
        return (T) r1.getMetadata();
    L15:
        return (T) r1.getMetadata().unwrapAs(r2);
    L9:
        return (T) this.cameraProperties;
    }
}
