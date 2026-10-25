package androidx.camera.camera2.pipe.compat;

import android.view.Surface;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.StreamId;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b`\u0018\u00002\u00020\u0001:\u0001\fJ,\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\u0006\u0010\n\u001a\u00020\u000bH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", _UrlKt.FRAGMENT_ENCODE_SET, "create", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result;", "cameraDevice", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "surfaces", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "captureSessionState", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "Result", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CaptureSessionFactory {

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result;", _UrlKt.FRAGMENT_ENCODE_SET, "Success", "Failed", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result$Failed;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result$Success;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface Result {

        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result$Failed;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result;", "<init>", "()V", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Failed implements Result {
            public static final Failed INSTANCE = null;

            static {
                INSTANCE = new Failed();
            }

            private Failed() {
            }
        }

        @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0002¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0014\u001a\u00020\u00132\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011HÖ\u0003¢\u0006\u0004\b\u0014\u0010\u0015R#\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00028\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R#\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\u0016\u001a\u0004\b\u0019\u0010\u0018¨\u0006\u001a"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result$Success;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory$Result;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "deferred", "Landroidx/camera/camera2/pipe/OutputId;", "Landroid/view/Surface;", "outputSurfaceMap", "<init>", "(Ljava/util/Map;Ljava/util/Map;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/util/Map;", "getDeferred", "()Ljava/util/Map;", "getOutputSurfaceMap", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Success implements Result {
            private final Map<StreamId, OutputConfigurationWrapper> deferred;
            private final Map<OutputId, Surface> outputSurfaceMap;

            /* JADX WARN: Multi-variable type inference failed */
            public Success(Map<StreamId, ? extends OutputConfigurationWrapper> r1, Map<OutputId, ? extends Surface> r2) {
                this.deferred = r1;
                this.outputSurfaceMap = r2;
            }

            public boolean equals(Object r5) {
                if (this != r5) goto L6;
                return true;
            L6:
                if ((r5 instanceof Success) == true) goto L8;
                return false;
            L8:
                Success r52 = (Success) r5;
                if (Intrinsics.areEqual(this.deferred, r52.deferred) == true) goto L12;
                return false;
            L12:
                if (Intrinsics.areEqual(this.outputSurfaceMap, r52.outputSurfaceMap) == true) goto L14;
                return false;
            L14:
                return true;
            }

            public final Map<StreamId, OutputConfigurationWrapper> getDeferred() {
                return this.deferred;
            }

            public final Map<OutputId, Surface> getOutputSurfaceMap() {
                return this.outputSurfaceMap;
            }

            public int hashCode() {
                return (this.deferred.hashCode() * 31) + this.outputSurfaceMap.hashCode();
            }

            public String toString() {
                return "Success(deferred=" + this.deferred + ", outputSurfaceMap=" + this.outputSurfaceMap + ')';
            }
        }
    }

    Result create(CameraDeviceWrapper r1, Map<StreamId, ? extends Surface> r2, CaptureSessionState r3);
}
