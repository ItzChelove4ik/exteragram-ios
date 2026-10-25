package androidx.camera.camera2.internal;

import android.hardware.camera2.CameraCharacteristics;
import android.util.Log;
import androidx.camera.camera2.config.CameraAppComponent;
import androidx.camera.camera2.config.CameraConfig;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.pipe.CameraDevices;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.DoNotDisturbException;
import androidx.camera.core.CameraInfo;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.InitializationException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.internal.StreamSpecsCalculator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.mvel2.MVEL;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/internal/CameraSelectionOptimizer;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CameraSelectionOptimizer {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\t\u0010\nJ;\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\f\u001a\u00020\u000b2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u000f2\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/internal/CameraSelectionOptimizer$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/camera2/pipe/CameraDevices;", "cameraDevices", _UrlKt.FRAGMENT_ENCODE_SET, "lensFacingInteger", _UrlKt.FRAGMENT_ENCODE_SET, "decideSkippedCameraIdByHeuristic", "(Landroidx/camera/camera2/pipe/CameraDevices;Ljava/lang/Integer;)Ljava/lang/String;", "Landroidx/camera/camera2/config/CameraAppComponent;", "cameraAppComponent", "Landroidx/camera/core/CameraSelector;", "availableCamerasSelector", _UrlKt.FRAGMENT_ENCODE_SET, "cameraIdList", "Landroidx/camera/core/internal/StreamSpecsCalculator;", "streamSpecsCalculator", "getSelectedAvailableCameraIds", "(Landroidx/camera/camera2/config/CameraAppComponent;Landroidx/camera/core/CameraSelector;Ljava/util/List;Landroidx/camera/core/internal/StreamSpecsCalculator;)Ljava/util/List;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraSelectionOptimizer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraSelectionOptimizer.kt\nandroidx/camera/camera2/internal/CameraSelectionOptimizer$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,155:1\n1563#2:156\n1634#2,3:157\n95#3,4:160\n146#3,4:164\n136#3,4:168\n*S KotlinDebug\n*F\n+ 1 CameraSelectionOptimizer.kt\nandroidx/camera/camera2/internal/CameraSelectionOptimizer$Companion\n*L\n45#1:156\n45#1:157,3\n77#1:160,4\n105#1:164,4\n146#1:168,4\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final String decideSkippedCameraIdByHeuristic(CameraDevices r7, Integer r8) {
            if (r8 != null) goto L34;
            return null;
        L34:
        L30:
            Camera2Logger r72 = Camera2Logger.INSTANCE;
            if (Logger.isErrorEnabled("CXCP") == false) goto L33;
            Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Received Do Not Disturb exception while deciding camera id to skip. Please turn off Do Not Disturb mode");
        L33:
            return null;
        L7:
            if (r8.intValue() != 1) goto L19;
            CameraMetadata r73 = CameraDevices.m168awaitCameraMetadataFpsL5FU$default(r7, CameraId.m228constructorimpl(MVEL.VERSION_SUB), null, 2, null);     // Catch: DoNotDisturbException -> L30
            if (r73 == null) goto L17;
            Integer r74 = (Integer) r73.get(CameraCharacteristics.LENS_FACING);     // Catch: DoNotDisturbException -> L30
            if (r74 == null) goto L33;
            if (r74.intValue() != 1) goto L33;
            return "1";
        L17:
            throw new IllegalStateException("Required value was null.");     // Catch: DoNotDisturbException -> L30
        L19:
            if (r8.intValue() != 0) goto L33;
            CameraMetadata r75 = CameraDevices.m168awaitCameraMetadataFpsL5FU$default(r7, CameraId.m228constructorimpl("1"), null, 2, null);     // Catch: DoNotDisturbException -> L30
            if (r75 == null) goto L29;
            Integer r76 = (Integer) r75.get(CameraCharacteristics.LENS_FACING);     // Catch: DoNotDisturbException -> L30
            if (r76 == null) goto L33;
            if (r76.intValue() != 0) goto L33;
            return MVEL.VERSION_SUB;
        L29:
            throw new IllegalStateException("Required value was null.");     // Catch: DoNotDisturbException -> L30
        }

        public final List<String> getSelectedAvailableCameraIds(CameraAppComponent r8, CameraSelector r9, List<String> r10, StreamSpecsCalculator r11) {
            ArrayList r1 = new ArrayList();     // Catch: IllegalStateException -> L13
            CameraDevices r2 = r8.getCameraDevices();     // Catch: IllegalStateException -> L13
            if (r9 != null) goto L34;
            return r10;
        L34:
            String r7 = decideSkippedCameraIdByHeuristic(r2, r9.getLensFacing());     // Catch: IllegalStateException -> L9
        L16:
            ArrayList r22 = new ArrayList();     // Catch: IllegalStateException -> L13
            Iterator<String> r102 = r10.iterator();     // Catch: IllegalStateException -> L13
        L18:
            if (r102.hasNext() == false) goto L23;
            String r4 = r102.next();     // Catch: IllegalStateException -> L13
            if (Intrinsics.areEqual(r4, r7) == true) goto L18;
            r22.add(r8.cameraBuilder().config(new CameraConfig(CameraId.m228constructorimpl(r4), null)).streamSpecsCalculator(r11).build().getCameraInternal().getCameraInfoInternal());     // Catch: IllegalStateException -> L13
            goto L18
        L23:
            Iterator<CameraInfo> r72 = r9.filter(r22).iterator();     // Catch: IllegalStateException -> L13
        L24:
            if (r72.hasNext() == false) goto L28;
            r1.add(((CameraInfoInternal) r72.next()).getCameraId());     // Catch: IllegalStateException -> L13
            goto L24
        L28:
            return r1;
        L9:
            e = move-exception;
            Camera2Logger r23 = Camera2Logger.INSTANCE;     // Catch: IllegalStateException -> L13
            if (Logger.isDebugEnabled("CXCP") == false) goto L15;
            Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Unable to get Metadata for cameraID 0 and/or 1", e);     // Catch: IllegalStateException -> L13
        L15:
            r7 = null;
        L13:
            e = move-exception;
            Camera2Logger r82 = Camera2Logger.INSTANCE;
            if (Logger.isErrorEnabled("CXCP") == false) goto L33;
            Log.e(Camera2Logger.access$getTRUNCATED_TAG$p(), "Error while accessing info about cameras.", e);
        L33:
            throw new InitializationException(e);
        }
    }

    static {
        Companion = new Companion(null);
    }
}
