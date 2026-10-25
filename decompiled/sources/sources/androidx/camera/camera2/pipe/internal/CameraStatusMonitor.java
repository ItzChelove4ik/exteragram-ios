package androidx.camera.camera2.pipe.internal;

import androidx.camera.camera2.pipe.CameraId;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\ba\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0001\rR\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "cameraAvailability", "Lkotlinx/coroutines/flow/StateFlow;", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", "getCameraAvailability", "()Lkotlinx/coroutines/flow/StateFlow;", "cameraPriorities", "Lkotlinx/coroutines/flow/SharedFlow;", _UrlKt.FRAGMENT_ENCODE_SET, "getCameraPriorities", "()Lkotlinx/coroutines/flow/SharedFlow;", "CameraStatus", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface CameraStatusMonitor extends AutoCloseable {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Unknown", "CameraPrioritiesChanged", "CameraAvailable", "CameraUnavailable", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static abstract class CameraStatus {

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus$CameraAvailable;", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCameraId-Dz_R5H8", "()Ljava/lang/String;", "Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class CameraAvailable extends CameraStatus {
            private final String cameraId;

            private CameraAvailable(String r1) {
                this.cameraId = r1;
            }

            public /* synthetic */ CameraAvailable(String r1, DefaultConstructorMarker r2) {
                this(r1);
            }

            /* renamed from: getCameraId-Dz_R5H8, reason: not valid java name */
            public final String m553getCameraIdDz_R5H8() {
                return this.cameraId;
            }

            public String toString() {
                return "CameraAvailable(camera=" + CameraId.m232toStringimpl(this.cameraId) + ')';
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus$CameraPrioritiesChanged;", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", "<init>", "()V", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class CameraPrioritiesChanged extends CameraStatus {
            public static final CameraPrioritiesChanged INSTANCE = null;

            static {
                INSTANCE = new CameraPrioritiesChanged();
            }

            private CameraPrioritiesChanged() {
            }

            public String toString() {
                return "CameraPrioritiesChanged";
            }
        }

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\t\u001a\u00020\nH\u0016R\u0013\u0010\u0002\u001a\u00020\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus$CameraUnavailable;", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", "cameraId", "Landroidx/camera/camera2/pipe/CameraId;", "<init>", "(Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getCameraId-Dz_R5H8", "()Ljava/lang/String;", "Ljava/lang/String;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class CameraUnavailable extends CameraStatus {
            private final String cameraId;

            private CameraUnavailable(String r1) {
                this.cameraId = r1;
            }

            public /* synthetic */ CameraUnavailable(String r1, DefaultConstructorMarker r2) {
                this(r1);
            }

            /* renamed from: getCameraId-Dz_R5H8, reason: not valid java name */
            public final String m554getCameraIdDz_R5H8() {
                return this.cameraId;
            }

            public String toString() {
                return "CameraUnavailable(camera=" + CameraId.m232toStringimpl(this.cameraId) + ')';
            }
        }

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus$Unknown;", "Landroidx/camera/camera2/pipe/internal/CameraStatusMonitor$CameraStatus;", "<init>", "()V", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Unknown extends CameraStatus {
            public static final Unknown INSTANCE = null;

            static {
                INSTANCE = new Unknown();
            }

            private Unknown() {
            }

            public String toString() {
                return "UnknownCameraStatus";
            }
        }

        public CameraStatus() {
        }
    }

    StateFlow<CameraStatus> getCameraAvailability();

    SharedFlow<Unit> getCameraPriorities();
}
