package androidx.camera.camera2.impl;

import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Log;
import android.util.Size;
import androidx.camera.camera2.compat.workaround.SupportedRepeatingSurfaceSizeKt;
import androidx.camera.core.Logger;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u0018\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0019\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b*\u00020\u0004H\u0002¢\u0006\u0002\u0010\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"DEFAULT_PREVIEW_SIZE", "Landroid/util/Size;", "getProperPreviewSize", "cameraProperties", "Landroidx/camera/camera2/impl/CameraProperties;", "displayInfoManager", "Landroidx/camera/camera2/impl/DisplayInfoManager;", "getOutputSizes", _UrlKt.FRAGMENT_ENCODE_SET, "(Landroidx/camera/camera2/impl/CameraProperties;)[Landroid/util/Size;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMeteringRepeating.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MeteringRepeating.kt\nandroidx/camera/camera2/impl/MeteringRepeatingKt\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,263:1\n119#2,4:264\n136#2,4:270\n6181#3,2:268\n*S KotlinDebug\n*F\n+ 1 MeteringRepeating.kt\nandroidx/camera/camera2/impl/MeteringRepeatingKt\n*L\n226#1:264,4\n257#1:270,4\n229#1:268,2\n*E\n"})
/* loaded from: classes3.dex */
public abstract class MeteringRepeatingKt {
    private static final Size DEFAULT_PREVIEW_SIZE = null;

    static {
        DEFAULT_PREVIEW_SIZE = new Size(640, 480);
    }

    public static final /* synthetic */ Size access$getDEFAULT_PREVIEW_SIZE$p() {
        return DEFAULT_PREVIEW_SIZE;
    }

    private static final Size[] getOutputSizes(CameraProperties r1) {
        StreamConfigurationMap r12 = (StreamConfigurationMap) r1.getMetadata().get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        if (r12 != null) goto L10;
        Camera2Logger r13 = Camera2Logger.INSTANCE;
        if (Logger.isErrorEnabled("CXCP") == false) goto L11;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Can not retrieve SCALER_STREAM_CONFIGURATION_MAP.");
        return null;
    L11:
        return null;
    L10:
        return r12.getOutputSizes(34);
    }

    public static final Size getProperPreviewSize(CameraProperties r10, DisplayInfoManager r11) {
        Size[] r102 = getOutputSizes(r10);
        if (r102 != null) goto L7;
        return DEFAULT_PREVIEW_SIZE;
    L7:
        if (r102.length == 0) goto L9;
        Size[] r0 = SupportedRepeatingSurfaceSizeKt.getSupportedRepeatingSurfaceSizes(r102);
        if (r0.length != 0) goto L13;
        boolean r1 = true;
    L14:
        if (r1 == true) goto L16;
        r102 = r0;
    L20:
        if (r102.length <= 1) goto L22;
        ArraysKt.sortWith(r102, new MeteringRepeatingKt$getProperPreviewSize$$inlined$sortBy$1());
    L22:
        Size r112 = r11.getPreviewSize();
        long r02 = Math.min(307200, r112.getWidth() * r112.getHeight());
        int r113 = r102.length;
        Size r3 = null;
        int r4 = 0;
    L23:
        if (r4 >= r113) goto L32;
        Size r5 = r102[r4];
        long r6 = r5.getWidth() * r5.getHeight();
        if (r6 == r02) goto L26;
        if (r6 > r02) goto L28;
        r4 = r4 + 1;
        r3 = r5;
        goto L23
    L28:
        if (r3 == null) goto L32;
        return r3;
    L26:
        return r5;
    L32:
        if (r3 == null) goto L34;
        return r3;
    L34:
        return r102[0];
    L16:
        Camera2Logger r03 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L20;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "No supported output size list, fallback to current list");
        goto L20
    L13:
        r1 = false;
        goto L14
    L9:
        return DEFAULT_PREVIEW_SIZE;
    }
}
