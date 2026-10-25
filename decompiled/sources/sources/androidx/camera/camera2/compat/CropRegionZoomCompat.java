package androidx.camera.camera2.compat;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.util.Log;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.internal.ZoomMath;
import androidx.camera.core.Logger;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0016\u001a\u00020\rH\u0016J\u0018\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/camera/camera2/compat/CropRegionZoomCompat;", "Landroidx/camera/camera2/compat/ZoomCompat;", "cameraProperties", "Landroidx/camera/camera2/impl/CameraProperties;", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;)V", "minZoomRatio", _UrlKt.FRAGMENT_ENCODE_SET, "getMinZoomRatio", "()F", "maxZoomRatio", "getMaxZoomRatio", "currentCropRect", "Landroid/graphics/Rect;", "sensorRect", "applyAsync", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "zoomRatio", "requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "resetAsync", "getCropSensorRegion", "computeCropRect", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nZoomCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZoomCompat.kt\nandroidx/camera/camera2/compat/CropRegionZoomCompat\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,215:1\n119#2,4:216\n119#2,4:220\n*S KotlinDebug\n*F\n+ 1 ZoomCompat.kt\nandroidx/camera/camera2/compat/CropRegionZoomCompat\n*L\n102#1:216,4\n135#1:220,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CropRegionZoomCompat implements ZoomCompat {
    private final CameraProperties cameraProperties;
    private Rect currentCropRect;
    private final Rect sensorRect;

    public CropRegionZoomCompat(CameraProperties r2) {
        this.cameraProperties = r2;
        this.sensorRect = (Rect) r2.getMetadata().get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
    }

    private final Rect computeCropRect(Rect r5, float r6) {
        if (ZoomMath.INSTANCE.nearZero$camera_camera2(r6) == false) goto L8;
        Camera2Logger r4 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L7;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "ZoomCompat: Invalid zoom ratio of 0.0f passed in, defaulting to 1.0f");
    L7:
        r6 = 1.0f;
    L8:
        float r42 = r5.width() / r6;
        float r0 = r5.height() / r6;
        float r62 = (r5.width() - r42) / 2.0f;
        float r52 = (r5.height() - r0) / 2.0f;
        return new Rect((int) r62, (int) r52, (int) (r62 + r42), (int) (r52 + r0));
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public Deferred<Unit> applyAsync(float r7, UseCaseCameraRequestControl r8) {
        Rect r72 = computeCropRect(this.sensorRect, r7);
        this.currentCropRect = r72;
        return UseCaseCameraRequestControl.setParametersAsync$default(r8, MapsKt.mapOf(TuplesKt.to(CaptureRequest.SCALER_CROP_REGION, r72)), null, null, 6, null);
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public Rect getCropSensorRegion() {
        Rect r0 = this.currentCropRect;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return this.sensorRect;
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public float getMaxZoomRatio() {
        Float r3 = (Float) this.cameraProperties.getMetadata().getOrDefault(CameraCharacteristics.SCALER_AVAILABLE_MAX_DIGITAL_ZOOM, Float.valueOf(getMinZoomRatio()));
        if (ZoomMath.INSTANCE.nearZero$camera_camera2(r3.floatValue()) == false) goto L10;
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L11;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Invalid max zoom ratio of " + r3 + " detected, defaulting to 1.0f");
        return 1.0f;
    L11:
        return 1.0f;
    L10:
        return r3.floatValue();
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public float getMinZoomRatio() {
        return 1.0f;
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public Deferred<Unit> resetAsync(UseCaseCameraRequestControl r3) {
        return UseCaseCameraRequestControl.removeParametersAsync$default(r3, CollectionsKt.listOf(CaptureRequest.SCALER_CROP_REGION), null, 2, null);
    }
}
