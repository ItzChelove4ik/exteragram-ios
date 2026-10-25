package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import android.util.Log;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.core.InitializationException;
import androidx.camera.core.Logger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\r"}, d2 = {"Landroidx/camera/camera2/internal/CameraCompatibilityFilter;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getBackwardCompatibleCameraIds", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "cameraDevices", "Landroidx/camera/camera2/pipe/CameraDevices;", "availableCameraIds", "isBackwardCompatible", _UrlKt.FRAGMENT_ENCODE_SET, "cameraId", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraCompatibilityFilter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraCompatibilityFilter.kt\nandroidx/camera/camera2/internal/CameraCompatibilityFilter\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,87:1\n85#2,4:88\n85#2,4:92\n146#2,4:96\n*S KotlinDebug\n*F\n+ 1 CameraCompatibilityFilter.kt\nandroidx/camera/camera2/internal/CameraCompatibilityFilter\n*L\n50#1:88,4\n65#1:92,4\n80#1:96,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraCompatibilityFilter {
    public static final CameraCompatibilityFilter INSTANCE = null;

    static {
        INSTANCE = new CameraCompatibilityFilter();
    }

    private CameraCompatibilityFilter() {
    }

    @JvmStatic
    public static final List<String> getBackwardCompatibleCameraIds(CameraDevices r5, List<String> r6) {
        ArrayList r0 = new ArrayList();
        Iterator<String> r62 = r6.iterator();
    L4:
        if (r62.hasNext() == false) goto L17;
        String r1 = r62.next();
        if (Intrinsics.areEqual(r1, MVEL.VERSION_SUB) == true) goto L16;
        if (Intrinsics.areEqual(r1, "1") == true) goto L16;
        if (isBackwardCompatible(r1, r5) == true) goto L12;
        Camera2Logger r2 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L4;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Camera " + r1 + " is filtered out because its capabilities do not contain REQUEST_AVAILABLE_CAPABILITIES_BACKWARD_COMPATIBLE.");
        goto L4
    L12:
        r0.add(r1);
    L16:
        r0.add(r1);
        goto L4
    L17:
        return r0;
    }

    @JvmStatic
    public static final boolean isBackwardCompatible(String r4, CameraDevices r5) {
        if (Intrinsics.areEqual(Build.FINGERPRINT, "robolectric") == false) goto L25;
        Camera2Logger r42 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L26;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "isBackwardCompatible method returns true because robolectric build detected.");
        return true;
    L26:
        return true;
    L25:
        CameraMetadata r52 = CameraDevices.m168awaitCameraMetadataFpsL5FU$default(r5, CameraId.m228constructorimpl(r4), null, 2, null);     // Catch: CameraAccessException -> L15
        if (r52 == null) goto L19;
        int[] r53 = (int[]) r52.get(CameraCharacteristics.REQUEST_AVAILABLE_CAPABILITIES);     // Catch: CameraAccessException -> L15
        if (r53 != null) goto L14;
        return false;
    L14:
        return ArraysKt.contains(r53, 0);
    L19:
        throw new IllegalStateException("Required value was null.");     // Catch: CameraAccessException -> L15
    L15:
        e = move-exception;
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isErrorEnabled("CXCP") == false) goto L24;
        Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Error while accessing metadata for cameraID: " + r4, e);
    L24:
        throw new InitializationException(e);
    }
}
