package androidx.camera.camera2.compat;

import android.graphics.Rect;
import android.hardware.camera2.CameraCharacteristics;
import android.os.Build;
import android.util.Log;
import android.util.Range;
import androidx.camera.camera2.compat.workaround.CameraMetadataSafeGetterKt;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.core.Logger;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001:\u0001\u0011J\u001e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000f\u001a\u00020\u0010H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/compat/ZoomCompat;", _UrlKt.FRAGMENT_ENCODE_SET, "minZoomRatio", _UrlKt.FRAGMENT_ENCODE_SET, "getMinZoomRatio", "()F", "maxZoomRatio", "getMaxZoomRatio", "applyAsync", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "zoomRatio", "requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "resetAsync", "getCropSensorRegion", "Landroid/graphics/Rect;", "Bindings", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ZoomCompat {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b'\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/camera/camera2/compat/ZoomCompat$Bindings;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class Bindings {
        public static final Companion Companion = null;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/compat/ZoomCompat$Bindings$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "provideZoomCompat", "Landroidx/camera/camera2/compat/ZoomCompat;", "cameraProperties", "Landroidx/camera/camera2/impl/CameraProperties;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nZoomCompat.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZoomCompat.kt\nandroidx/camera/camera2/compat/ZoomCompat$Bindings$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,215:1\n1761#2,2:216\n1763#2:222\n119#3,4:218\n*S KotlinDebug\n*F\n+ 1 ZoomCompat.kt\nandroidx/camera/camera2/compat/ZoomCompat$Bindings$Companion\n*L\n69#1:216,2\n69#1:222\n70#1:218,4\n*E\n"})
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final ZoomCompat provideZoomCompat(CameraProperties r5) {
                if (Intrinsics.areEqual("robolectric", Build.FINGERPRINT) == false) goto L20;
                List<CameraCharacteristics.Key<Rect>> r4 = NoOpZoomCompat.Companion.getRequiredCharacteristics();
                if ((r4 instanceof Collection) == true) goto L7;
            L9:
                Iterator<T> r42 = r4.iterator();
            L11:
                if (r42.hasNext() == false) goto L26;
                CameraCharacteristics.Key r0 = (CameraCharacteristics.Key) r42.next();
                Camera2Logger r1 = Camera2Logger.INSTANCE;
                if (Logger.isWarnEnabled("CXCP") == false) goto L16;
                Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Failed to read " + r0 + " for zoom features.");
            L16:
                if (r5.getMetadata().get(r0) != null) goto L11;
                return new NoOpZoomCompat(r5);
            L26:
                return new CropRegionZoomCompat(r5);
            L7:
                if (r4.isEmpty() == false) goto L9;
            L20:
                if (Build.VERSION.SDK_INT < 30) goto L26;
                Range<Float> r43 = CameraMetadataSafeGetterKt.getControlZoomRatioRangeSafely(r5.getMetadata());
                if (r43 == null) goto L26;
                return new AndroidRZoomCompat(r5, r43);
            }
        }

        static {
            Companion = new Companion(null);
        }
    }

    Deferred<Unit> applyAsync(float r1, UseCaseCameraRequestControl r2);

    Rect getCropSensorRegion();

    float getMaxZoomRatio();

    float getMinZoomRatio();

    Deferred<Unit> resetAsync(UseCaseCameraRequestControl r1);
}
