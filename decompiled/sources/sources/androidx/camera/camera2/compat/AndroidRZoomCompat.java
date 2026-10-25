package androidx.camera.camera2.compat;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import android.util.Range;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.pipe.CameraMetadata;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000b¨\u0006\u0017"}, d2 = {"Landroidx/camera/camera2/compat/AndroidRZoomCompat;", "Landroidx/camera/camera2/compat/ZoomCompat;", "cameraProperties", "Landroidx/camera/camera2/impl/CameraProperties;", "range", "Landroid/util/Range;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;Landroid/util/Range;)V", "minZoomRatio", "getMinZoomRatio", "()F", "maxZoomRatio", "getMaxZoomRatio", "applyAsync", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "zoomRatio", "requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "resetAsync", "getCropSensorRegion", "Landroid/graphics/Rect;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AndroidRZoomCompat implements ZoomCompat {
    private final CameraProperties cameraProperties;
    private final Range<Float> range;

    public AndroidRZoomCompat(CameraProperties r1, Range<Float> r2) {
        this.cameraProperties = r1;
        this.range = r2;
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public Deferred<Unit> applyAsync(float r7, UseCaseCameraRequestControl r8) {
        float r0 = getMinZoomRatio();
        if (r7 <= getMaxZoomRatio()) goto L5;
    L13:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
        return null;
    L5:
        if (r0 > r7) goto L13;
        Map r1 = MapsKt.mutableMapOf(new Pair[]{TuplesKt.to(AndroidRZoomCompat$$ExternalSyntheticApiModelOutline0.m(), Float.valueOf(r7))});
        if (Build.VERSION.SDK_INT < 34) goto L12;
        if (CameraMetadata.Companion.getSupportsZoomOverride(this.cameraProperties.getMetadata()) == false) goto L12;
        Api34Compat.setSettingsOverrideZoom(r1);
    L12:
        return UseCaseCameraRequestControl.setParametersAsync$default(r8, r1, null, null, 6, null);
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public Rect getCropSensorRegion() {
        return (Rect) this.cameraProperties.getMetadata().get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE);
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public float getMaxZoomRatio() {
        return ((Number) this.range.getUpper()).floatValue();
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public float getMinZoomRatio() {
        return ((Number) this.range.getLower()).floatValue();
    }

    @Override // androidx.camera.camera2.compat.ZoomCompat
    public Deferred<Unit> resetAsync(UseCaseCameraRequestControl r3) {
        List r2 = CollectionsKt.mutableListOf(new CaptureRequest.Key[]{AndroidRZoomCompat$$ExternalSyntheticApiModelOutline0.m()});
        if (Build.VERSION.SDK_INT < 34) goto L6;
        r2.add(AndroidRZoomCompat$$ExternalSyntheticApiModelOutline1.m());
    L6:
        return UseCaseCameraRequestControl.removeParametersAsync$default(r3, r2, null, 2, null);
    }
}
