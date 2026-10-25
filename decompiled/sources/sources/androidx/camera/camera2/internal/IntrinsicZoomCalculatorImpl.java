package androidx.camera.camera2.internal;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.util.Size;
import android.util.SizeF;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.core.impl.utils.TransformUtils;
import androidx.core.util.Preconditions;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\nJ\f\u0010\u000b\u001a\u00020\u0007*\u00020\tH\u0002J\f\u0010\f\u001a\u00020\u0007*\u00020\tH\u0002J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007H\u0003J\f\u0010\u0011\u001a\u00020\u000e*\u00020\tH\u0002J\f\u0010\u0012\u001a\u00020\u000e*\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Landroidx/camera/camera2/internal/IntrinsicZoomCalculatorImpl;", "Landroidx/camera/camera2/internal/IntrinsicZoomCalculator;", "cameraDevices", "Landroidx/camera/camera2/pipe/CameraDevices;", "<init>", "(Landroidx/camera/camera2/pipe/CameraDevices;)V", "calculateIntrinsicZoomRatio", _UrlKt.FRAGMENT_ENCODE_SET, "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraMetadata;", "(Landroidx/camera/camera2/pipe/CameraMetadata;)Ljava/lang/Float;", "getDefaultFocalLength", "getSensorHorizontalLength", "focalLengthToViewAngleDegrees", _UrlKt.FRAGMENT_ENCODE_SET, "focalLength", "sensorLength", "getDefaultViewAngleDegrees", "getDefaultCameraDefaultViewAngleDegrees", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nIntrinsicZoomCalculator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 IntrinsicZoomCalculator.kt\nandroidx/camera/camera2/internal/IntrinsicZoomCalculatorImpl\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,256:1\n86#2,2:257\n1869#3,2:259\n*S KotlinDebug\n*F\n+ 1 IntrinsicZoomCalculator.kt\nandroidx/camera/camera2/internal/IntrinsicZoomCalculatorImpl\n*L\n84#1:257,2\n226#1:259,2\n*E\n"})
/* loaded from: classes3.dex */
public final class IntrinsicZoomCalculatorImpl implements IntrinsicZoomCalculator {
    private final CameraDevices cameraDevices;

    public IntrinsicZoomCalculatorImpl(CameraDevices r1) {
        this.cameraDevices = r1;
    }

    private final int focalLengthToViewAngleDegrees(float r5, float r6) {
        boolean r1 = true;
        if (r5 <= 0.0f) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "Focal length should be positive.");
        if (r6 > 0.0f) goto L10;
        r1 = false;
    L10:
        Preconditions.checkArgument(r1, "Sensor length should be positive.");
        int r4 = (int) Math.toDegrees(2.0d * Math.atan(r6 / (2.0f * r5)));
        Preconditions.checkArgumentInRange(r4, 0, 360, "The provided focal length and sensor length result in an invalid view angle degrees.");
        return r4;
    L5:
        r0 = false;
        goto L6
    }

    private final int getDefaultCameraDefaultViewAngleDegrees(CameraMetadata r9) {
        Iterator r0 = ((List) Preconditions.checkNotNull(CameraDevices.m167awaitCameraIdsSeavPBo$default(this.cameraDevices, null, 1, null), "Failed to get available camera IDs")).iterator();     // Catch: Exception -> L11
    L4:
        if (r0.hasNext() == false) goto L10;
        String r1 = ((CameraId) r0.next()).m233unboximpl();     // Catch: Exception -> L11
        CameraMetadata r3 = (CameraMetadata) Preconditions.checkNotNull(CameraDevices.m168awaitCameraMetadataFpsL5FU$default(this.cameraDevices, r1, null, 2, null), "Failed to get CameraMetadata for " + CameraId.m232toStringimpl(r1));     // Catch: Exception -> L11
        CameraCharacteristics.Key r4 = CameraCharacteristics.LENS_FACING;     // Catch: Exception -> L11
        if (((Number) Preconditions.checkNotNull(r3.get(r4), "Failed to get CameraCharacteristics.LENS_FACING for " + CameraId.m232toStringimpl(r1))).intValue() != ((Number) Preconditions.checkNotNull(r9.get(r4), "Failed to get the required LENS_FACING for " + CameraId.m232toStringimpl(r9.mo237getCameraDz_R5H8()))).intValue()) goto L4;
        return focalLengthToViewAngleDegrees(getDefaultFocalLength(r3), getSensorHorizontalLength(r3));
    L10:
        throw new IllegalStateException("Could not find the default camera for " + CameraId.m232toStringimpl(r9.mo237getCameraDz_R5H8()));     // Catch: Exception -> L11
    L11:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Failed to get a valid view angle", e);
        return 0;
    }

    private final float getDefaultFocalLength(CameraMetadata r4) {
        float[] r3 = (float[]) Preconditions.checkNotNull(r4.get(CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS), "The focal lengths can not be empty.");
        if (r3.length != 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkState(!r0, "The focal lengths can not be empty.");
        return r3[0];
    L5:
        r0 = false;
        goto L6
    }

    private final int getDefaultViewAngleDegrees(CameraMetadata r2) {
        return focalLengthToViewAngleDegrees(getDefaultFocalLength(r2), getSensorHorizontalLength(r2));
    L4:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Failed to get a valid view angle", e);
        return 0;
    }

    private final float getSensorHorizontalLength(CameraMetadata r4) {
        SizeF r3 = (SizeF) Preconditions.checkNotNull(r4.get(CameraCharacteristics.SENSOR_INFO_PHYSICAL_SIZE), "The sensor size can't be null.");
        Rect r0 = (Rect) Preconditions.checkNotNull(r4.get(CameraCharacteristics.SENSOR_INFO_ACTIVE_ARRAY_SIZE), "The sensor orientation can't be null.");
        Size r1 = (Size) Preconditions.checkNotNull(r4.get(CameraCharacteristics.SENSOR_INFO_PIXEL_ARRAY_SIZE), "The active array size can't be null.");
        int r42 = ((Number) Preconditions.checkNotNull(r4.get(CameraCharacteristics.SENSOR_ORIENTATION), "The pixel array size can't be null.")).intValue();
        Size r02 = TransformUtils.rectToSize(r0);
        if (TransformUtils.is90or270(r42) == false) goto L6;
        r3 = TransformUtils.reverseSizeF(r3);
        r02 = TransformUtils.reverseSize(r02);
        r1 = TransformUtils.reverseSize(r1);
    L6:
        return (r3.getWidth() * r02.getWidth()) / r1.getWidth();
    }

    @Override // androidx.camera.camera2.internal.IntrinsicZoomCalculator
    public Float calculateIntrinsicZoomRatio(CameraMetadata r2) {
        return Float.valueOf(getDefaultCameraDefaultViewAngleDegrees(r2) / getDefaultViewAngleDegrees(r2));
    L4:
        e = move-exception;
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L12;
        android.util.Log.e("CXCP", "Failed to get the intrinsic zoom ratio", e);
        return null;
    L12:
        return null;
    }
}
